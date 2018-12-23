# csv-2-cassandra

Chunk csv files in s3 and associate with tables in cassandra

## Purpose of this exercise
Read files line by line from s3 (stored as CSV file), read
data into a cassandra table associated with a file type. Create
a second table that associates an `id` (given) with the minimum
observation date for that record and the maximum observation date
for that record.

## Case study
In a hypothetical clinical trial of Compound1, two types of
data are presented, one in the form of a strictly observational model,
`ObservationModel` and the other with site information,
`ObservationModelWithSite`. These data are provided via csv files from
sites (different file types).

Given these data:

1. Separate the models based on the data contents
2. Sort by Compound (May want to add additional compounds in the future)
3. Retrieve a mapping of id (in this case patient id) to the minimum
observation date (when the compound was first administered) and the
maximum observation date.

## Running locally

1. Get a local docker container with cassandra

`docker run --name cassandra-test -d -p 9042:9042 -v <local>:<docker> cassandra:latest` # first time

`docker start cassandra-test` # after the container is created, just start it

2. Drop into the cassandra shell:

`docker exec -it cassandra-test cqlsh`
