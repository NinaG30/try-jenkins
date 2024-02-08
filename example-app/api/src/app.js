// Importing the required modules
const express = require('express'); // Importing the Express module
const mongoose = require('mongoose'); // Importing the Mongoose module

// Creating an instance of the Express application
const app = express();

// Setting the port number for the server
const port = 3000;

// Connecting to the MongoDB database
mongoose.connect('mongodb://localhost/example-app').then(() => {
    console.log('Connected to MongoDB'); // Logging a success message if the connection is successful
}).catch((err) => {
    console.log('Failed to connect to MongoDB', err); // Logging an error message if the connection fails
});

// Defining a route for the root URL
app.get('/', (req, res) => {
    res.send('Hello World!'); // Sending a response with the message "Hello World!"
});

// Starting the server and listening on the specified port
app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`); // Logging a message indicating that the server is running
});