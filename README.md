# csv-2-cassandra

Chunk csv files in s3 and associate with tables in cassandra

Read files line by line from s3 (stored as CSV file), read those
data into a cassandra table associated with a file type. Create
a second table that associates an `id` (given) with the minimum
observation date for that record and the maximum observation date
for that record.

