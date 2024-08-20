~~## MongoDB

It is a NoSQL db which is document oriented and stores data in BSON(equivalent to JSON) format.
Each database has its own files, and it is a container for collections.

Each collection is a group of documents(equivalent to row in RDBMS) and a collection is equivalent to a RDBMS table.
Data is semi structured and can be queried using document oriented query languages.

Features of NoSQL DB:

1. Dynamic Schema
2. Horizontal scalability
3. Document-based
4. Distributed and high availability

Sample mongodb document:

```json
{
  "title": "Geeksforgeeks",
  "by": "ABC",
  "url": "https://www.geeksforgeeks.org",
  "type": "NoSQL"
} 
```

### Datatypes and Syntax

#### Cursor

Suppose we have a db named gfg, collection named student and some data in it.
To find all data, this will return a cursor with all the data present in student collection: use db.student.find()
.pretty()

Iterating through the cursor:

1. using forEach
   var mycursor = db.student.find({studentId:3}).pretty()
   mycursor.forEach(printjson)

2. using next()
   var mycursor = db.student.find().pretty()
   while(mycursor.hasNext()){
   sout(converToJson(mycursor.next()))
   }

3. using toArray to access using index
   var mycursor = db.student.find().pretty()
   var docs = mycursor.toArray()
   var resultdoc = docs[0]
   sout(resultdoc)

#### Datatypes:

1. String
2. Integer
3. double
4. boolean
5. null
6. array
7. object(for nested data like a author has book object)
8. objectId -> unique id created by default if not already present(_id is the field name)
9. undefined
10. Date
11. binary
12. javascript -> javascript code can be stored in document
13. symbol -> similar to string
14. regular expression
15. timestamp
16. decimal (introduced in v3.4)

#### Create Database

```shell
use databaseName
```

use databaseName -> this will switch to the specified db if it exists otherwise it will create the db and then switch

#### Drop database

```shell
use databaseName
db.dropDatabase()
```

#### Create collection

```shell
use databaseName
db.createCollection('Student');
```

#### Drop collection

drop() method returns true if collection is dropped. It collection doesn't exist it doesn't do anything.

```shell
use databaseName
db.student.drop()
```

#### Inserting

```shell
use databaseName
db.collectionName.insertOne({name:"John", surname:"Doe", age:30, isActive:true})
db.collectionName.insertMany([{name:"John", surname:"Doe", age:30, isActive:true, _id: 2}, {name:"James", surname:"Doe", age:40, isActive:true, _id: 3}])

```

#### Updating

With updateOne, the update operation will fail if the document size is changed.

```shell
db.COLLECTION_NAME.update({SELECTION_CRITERIA}, {$set:{UPDATED_DATA}}, {
     upsert: <boolean>,
     multi: <boolean>,
     writeConcern: <document>,
     collation: <document>,
     arrayFilters: [ <filterdocument1>, ... ],
     hint:  <document|string>        
   })
   
db.updateOne({...})
db.updateMany([{...}])
db.replaceOne()
   
db.demo.updateOne({ name: "Alice" },
   { $set: { age: 26 } })
   
#Below creates the record if it doesn't exists:
db.demo.updateOne(
   { name: "Charlie" },
   { $set: { age: 30 } },
   { upsert: true }
)
```

#### Deleting

```shell
# Below query deletes the first document from 'demo' collection:
db.demo.deleteOne({})

# If there are more than 1 document with the matching filter than first one will be deleted
db.demo.deleteOne({age:30})

# Below query will delete all matching documents
db.demo.deleteMany({age:30})

# To delete all documents
db.demo.deleteMany({})
```

#### Querying

1. Find all documents: db.collectionName.find().pretty()
2. Find 1 document: db.collectionName.findOne()
3. Find document through equality search: db.collectionName.find({author:{$eq:"devil"}}).pretty()
4. Find through greater than or less than: db.collection_name.find({< key > : {$gte : < value >}})
   or
   db.collection_name.find({< key > : {$lte : < value >}})
5. combining conditions using 'and': db.article.find({$and:[{level: {$eq: "high" }},{level: {$exists : true}}]})
   .pretty()
   **and operator:** {$and : [ first condition, second condition]}
   **first condition(level == “high”):** { level : {$eq  :  “high”}}
   **second condition:** {level : {$exists : true}}
6. combining using 'and' & 'or': db.article.find({
   $or: [
   { level: "basic", author: "Aditya" },
   { $and: [
   { level: "medium" },
   { author: "Rakesh" }
   ]
   }
   ]
   }).pretty()

```json
{
  "id": 1,
  "name": {
    "first": "A",
    "middle": "B",
    "last": "C"
  },
  "courseDetails": {
    "name": "Intro to Java",
    "startingDate": "19-08-2024",
    "payment": {
      "amount": 1000,
      "paid": true
    },
    "state": "MP",
    "contactDetails": 89765403210,
    "graduationYear": 2010,
    "branch": "CSE"
  }
}
```

```shell
db.Courses.find({name: {first: "A", 
						middle: "B", 
						last: "C"}}).pretty()
						
db.Courses.find({"courseDetails.name":"Intro to Java"}).pretty()

db.Courses.find({"name.first": {$in: ["A", "C"]}}).pretty()

# works as and
db.Courses.find({"courseDetails.name": "Sudo GATE 2020",
				"name.first": "A"}).pretty()
				
db.Courses.find( {"courseDetails": {$elemMatch: {"name": "Intro to Java"} } })

db.Courses.find( { "courseDetails": {$elemMatch: {name : {$in: ["Intro to Java", "Intro to Python"] } } } } )

# Belo query find documents where email field doesn't exist or value of phone field is null 
db.students.find({
  $or: [
    { email: { $exists: false } },
    { phone: null }
  ]
})

db.students.findOneAndUpdate(
  { name: "vishal" },
  { $set: { score: 220 } },
  { returnDocument: "after" }
)

# Below query finds the record and update the score and return the updated record.  
db.student.findAndModify( { query:{ name:"vishal" },
                          update:{$inc: {score:4} }, new:true})
                          
db.student.findOneAndDelete({name:"Bablue"})

# sort the resulting documents in descending order of age
db.student.findOneAndDelete({age:17},{sort:{age:-1}})

# find and sort
db.demo.find().sort({"courseDetails.branch":1})

{field: {$gt: value}}
db.student.find({age: {$gt: 15}})
db.student.find({age: {$gte: 15}})
db.student.find({age: {$lte: 15}})
db.student.find({age: {$lt: 15}})
db.student.find({age: {eq: 15}})
db.student.find({age: {$in: [15, 17]}})
db.student.find({age: {$nin: [15, 17]}})
db.Courses.find({"courseDetails.payment.amount": {$gt: 1000}}).pretty()
db.Courses.find({$and: [ {branch: "CSE"}, {joiningYear: 2018} ] } ).pretty()
db.Courses.find({$or: [{branch: "CSE"}, {joiningYear: 2018}]}).pretty()
db.Courses.find({$nor: [{branch: "CSE"}, {joiningYear: 2018}]}).pretty()
db.Courses.find({branch: {$not: "CSE"}})
db.Courses.find( {joiningYear: {$not: {$gte: 2017} } } )

# projection(select specific columns)
db.demo.find({}, {name:1}) #-> will return name and _id in result instead of full data
db.demo.find({}, {name:1, _id:0}) # -> will return only name in result.

# $min, $max, $mul
db.Courses.updateOne({"name":"John", {$inc: {"courseDetails.payment.amount": 4000}}})


db.demo.find({
  $expr: { $gt: {'field1', 'field2'} }
})

db.demo.find({
  field: { $regex: /[A-Z]+/ }
})

# to search for contains
# create text index on the field to search
# if text index is created on multiple fields then below query will search for the value specified in all those fields
db.demo.find({
  $text: {
    $search: "valueToSearch"
  }
})

# below query will search for the records where the specified field is even(field%2==0)
db.demo.find({
  field: {
    $mod: [2, 0]
  }
})

db.demo.find({
  field: {
    $mod: [2, 0]
  }
}).size() # -> returns the count of records where the specified field is even

# below query will give record where arrayField has size 3
db.demo.find( { arrayField: { $size: 3 } } )

# $and is used for getting results after combining the query
db.demo.find({
  $and: [ { age: { $gt: 5 } }, { age: { $lt: 11 } } ]
})
db.demo.find({
  { age: { $gt: 5 } }, { age: { $lt: 11 } } 
})
# both the queries will return different results 
# first query will check if age > 5 and age < 11
# second query will check age < 11 because in json if we query on same field multiple times the last one is given preference

# below query will check that in products array if apple is present and any of the fruit quantity is = 11
db.demo.find({
  $and: [{ "products.name": "apple" }, {"products.qty": 11}]
})

# if we want to search for something like the array should have apple fruit and quantity of apple should be 11 then we should use elem match

db.demo.find({
  "products": { $elemMatch: { name: 'apple', { quantity: 11 } } }
})

# if Palak's age is greater than 23 then it will be set to 23(basically set the age to min(age, 23))
db.demo.findOne( { name:'Palak', { $min: { age: 23 } } } )


```

#### Indexing

Indexing can help in faster search but slower write operations as indexes has to be rebuilt once a new record is
inserted.

```shell
# index created on name and in ascending order
db.demo.createIndex({name:1})
# index created on name and age, this will be used when we query using either name or both name and age 
# but it won't be used if we query just using age 
db.demo.createIndex({name: 1, age: 1})


#{fieldName: 1} -> ascending order
#{fieldName: -1} -> descending order
db.demo.createIndex({fieldName: 1})
db.demo.dropIndex('indexName')
db.demo.getIndexes()

db.demo.find().explain()  # -> this query will explain how the find() is working

# below will create unique index on name 
db.demo.createIndex({name: 1}, {unique: true})

# below query will create index on age field but only when it is greater than 22.
db.demo.createIndex({age: 1}, {partialFilterIndex: {age : {$gt: 22} } } )

# Covered query: a query in which all fields in query are part of index and all the fields returned in response are in the same index.
# so for a covered query the data will be returned from the index

db.demo.find({...}).explain("executionStats")
db.demo.find({...}).explain("allExecutionPlan")

# Text index: search like google search engine with partial text also.
# There can be only 1 text index per collection. But in that 1 text index we can specify multiple fields.

db.demo.createIndex({fieldName:"text"})
db.demo.createIndex({fieldName1:"text", fieldName2: "text"})


```

#### Aggregation

To write aggregate query/ pipeline operation. It groups the data from multiple documents to a single document.
The aggregation process in mongoDB consist of several stages, each stage transforming the data in some way.
The output of 1 stage is fed as the input to next stage and so on until the final stage produces the desired result.

```shell
# find those students whose branch is EE
db.demo.aggregate([{$match:{"courseDetails.branch":"EE"}}])

# group students by branch, _id specifies the value against which the group will be created
db.demo.aggregate( [ { $group: { _id: "$courseDetails.branch" } } ] )
Output: [ { _id: 'EE' }, { _id: 'ECE' }, { _id: 'CSE' }, { _id: 'IPE' } ]

# group students by branch and show the name of those students
db.demo.aggregate( [ { $group: { _id: "$courseDetails.branch", names: { $push: "$name" } } } ] )
output: [
  { _id: 'EE', names: [ 'Sneha', 'Pooja' ] },
  { _id: 'CSE', names: [ 'Pooja' ] },
  { _id: 'IPE', names: [ 'Priya', 'Ayushi' ] },
  { _id: 'ECE', names: [ 'Nidhi' ] }
]

# get full documents group by branch
db.demo.aggregate( [ { $group: { _id: "$courseDetails.branch", students: { $push: "$$ROOT" } } } ] )
output: [
  {
    _id: 'ECE',
    students: [
      {
        _id: 3,
        name: 'Nidhi',
        lastName: 'Bidwal',
        graduationYear: 2019,
        isPassed: true,
        courseDetails: { branch: 'ECE', rollNumber: 'BM35' }
      }
    ]
  },
  {
    _id: 'CSE',
    students: [
      {
        _id: 2,
        name: 'Pooja',
        lastName: 'Soni',
        graduationYear: 2019,
        isPassed: true,
        courseDetails: { branch: 'CSE', rollNumber: 'CS35' }
      }
    ]
  },
  {
    _id: 'IPE',
    students: [
      {
        _id: 7,
        name: 'Priya',
        lastName: 'Kadam',
        graduationYear: 2019,
        isPassed: true,
        courseDetails: { branch: 'IPE', rollNumber: 'IP39' }
      },
      {
        _id: 8,
        name: 'Ayushi',
        lastName: 'Jain',
        graduationYear: 2019,
        isPassed: true,
        courseDetails: { branch: 'IPE', rollNumber: 'IP05' }
      }
    ]
  },
  {
    _id: 'EE',
    students: [
      {
        _id: 9,
        name: 'Sneha',
        lastName: 'Agrawal',
        graduationYear: 2019,
        isPassed: true,
        courseDetails: { branch: 'EE', rollNumber: 'EE48' }
      },
      {
        _id: 4,
        name: 'Pooja',
        lastName: 'Patle',
        graduationYear: 2019,
        isPassed: true,
        courseDetails: { branch: 'EE', rollNumber: 'EE37' }
      }
    ]
  }
]

# find count of students whose branch is 'EE'
db.demo.aggregate( [ {$match: {"courseDetails.branch":"EE"} }, {$group:{_id:"$courseDetails.branch", countOfStudents:{ $sum: 1 } } }] )
Output: [ { _id: 'EE', countOfStudents: 2 } ]

# count of students in each branch
db.demo.aggregate( [ {$group:{_id:"$courseDetails.branch", countOfStudents:{ $sum: 1 } } }] )
Output: [
  { _id: 'IPE', countOfStudents: 2 },
  { _id: 'CSE', countOfStudents: 1 },
  { _id: 'EE', countOfStudents: 2 },
  { _id: 'ECE', countOfStudents: 1 }
]

db.demo.aggregate( [ { $group: {_id:"$courseDetails.branch", countOfStudents:{ $sum: 1 } } }, {$sort: { countOfStudents: -1 } } ] )

# unwind: it will flatten the array, lets say we have a student who has 3 hobbies and if we do unwind operation on hobbies than in the result we will get 3 records for same student as the hobbies are unwinded now.
db.demo.aggregate([{unwind: "$hobbies"}])

db.demo.aggregate( [ {unwind: "$hobbies"}, {$group: {_id: "$courseDetails.branch", Hobbies: {$push: $hobbies} } } ] )
# group by hobbies and count of each hobby
db.demo.aggregate( [ {unwind: "$hobbies"}, {$group: {_id: "$hobbies", count: {sum: 1} } } ] )

# average age of all students
db.demo.aggregate( [ { $group: {_id: null, averageAge: {$avg: $age } } } ] )
# _id = null mean that all the documents in the collection will be grouped together in a single group

# total hobbies for all students without removing duplicates
db.demo.aggregate([{$unwind: "$hobbies"}, {$group: {_id:null, total: {$sum: 1 } } } ] )
# below query will give error if for some students the hobbies array is null
db.demo.aggregate([{$group: {_id:null, total: {$sum: {$size: "$hobbies" } } } } ] )
# so we can use this: 
db.demo.aggregate([{$match:{"hobbies":{"$exists":true}}},{$group: {_id:null, total: {$sum: {$size: "$hobbies"}}}}])
Ouput: [ { _id: null, total: 11 } ]
# if hobbies is null then consider as empty array
db.demo.aggregate([{$group: {_id:null, total: {$sum: {$size: { $ifNull: [ "$hobbies", [] ] } } } } } ] )




```

/**

* Paste one or more documents here
  */
  {
  "name": "James",
  "lastName": "Doe",
  "age": 31,
  "graduationYear": 2010,
  "courseDetails":{
  "name":"CSE",
  "isCompleted":true,
  "marks":{
  "dsa":90,
  "oop":91,
  "dbms":92
  }
  }{
  }

