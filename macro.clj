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