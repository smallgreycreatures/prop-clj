

;(defmacro select [columns from table where op orderby col]
(defmacro select [columns from table where op orderby col]
	`(sqlSelect ~columns (sort-by ~col (sqlFilter ~op ~table)))
)

(defn sqlFilter [op table]
	(filter 
		(fn [x] 
			(
				(get op 1)
				((get op 0) x) 
				(get op 2)
			)
		)
		table
	)
)

(defn sqlSelect [columns table]
	(for [current table] (select-keys current (into [] (reverse columns))))
)