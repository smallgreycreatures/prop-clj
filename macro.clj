;(import java.io.FileReader java.io.File)
;(safe [s (new FileReader (new File "igelkott2.txt"))] (.read s))

(defmacro safe [references expression]
	`(if ~(vector? references)
		(let [~(get references 0) ~(get references 1)]
			(try
				~expression
				(catch Exception e# e#)
				(finally 
					(if (instance? java.io.Closeable ~(get references 0)) 
						(.close ~(get references 0))
					)
				)
			)
		) (println "Arguments (Vector, Expression)")
	)
)

; Reflections:
; Detta macro skulle inte fungera att definera som en funktion eftersom argumenten som skickas in inte kan utvärderas direkt utan 
; komplikationer. Detta är nämligen en av skillnaderna mellan ett macro och en funktion; att argumenten till en funktion utvärderas när
; de skickas in till funktionen emedan de för ett macro ej gör det. I detta fall så skulle man ifall argumenten utvärderades direkt 
; få problem med att exceptions uppstår som aldrig fångas. Om man skickar in en fil och får FileNotFoundException från File men också 
; har öppnat en FileReader så skulle man med en funktion ej kunna stänga FileReadern, vilket man med detta macro alltid gör.
