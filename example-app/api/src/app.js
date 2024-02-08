const express = require('express');
const mongoose = require('mongoose');
const app = express();
const port = 3000;

mongoose.connect('mongodb://localhost/example-app').then(() => {
    console.log('Connected to MongoDB');
    }).catch((err) => {
    console.log('Failed to connect to MongoDB', err);
});

app.get('/', (req, res) => {
    res.send('Hello World!');
});

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});