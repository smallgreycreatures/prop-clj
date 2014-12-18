

;(defmacro select [columns from table where op orderby col]
(defmacro select [cols table op col]
	`(sqlSelect ~cols (sort-by ~col (sqlFilter ~op ~table)))
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

(defn sqlSelect [cols table]
	(doseq [current table] (concat selected (select-keys current [:name])))
)