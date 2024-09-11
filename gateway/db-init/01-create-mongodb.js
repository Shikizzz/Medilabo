
// Create DB and collection
db = new Mongo().getDB("medilabo");
db.createCollection("notes", { capped: false });