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

# $min, $max, $mul
db.Courses.updateOne({"name":"John", {$inc: {"courseDetails.payment.amount": 4000}}})

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
  }
  }

