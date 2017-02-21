To use the setup.js:
1. Change <serverAddress> and <password> to valide strings.
2. If not installed, install mongoDB as stated here: https://docs.mongodb.com/v3.4/installation/ to the server the database should run on.
Important, the current openAPE version is tested against MongoDB 3.4.1.
3. Start the mongo shell as stated here: https://docs.mongodb.com/manual/mongo/ 
4. Copy setup.js into the mongo installation bin folder.
5. Use the 'load("setup.js")' command.
