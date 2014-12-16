(defmacro safe [references expression]
	`(if (instance? java.io.Closeable ~(get references 1))
	(let [~(get references 0) ~(get references 1)]
	(try
		~expression
	(finally 
		(if (instance? java.io.Closeable ~(get references 0)) (.close ~(get references 0))))))))