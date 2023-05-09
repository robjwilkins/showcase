print("Start Adding the Users.");
db = db.getSiblingDB("showcase");
db.createUser({
    user: "db",
    pwd: "dbpass",
    roles: [{ role: "readWrite", db: "showcase" }],
});
print("End Adding the User Roles.");
