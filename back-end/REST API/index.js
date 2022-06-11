'use strict';
const express = require('express');
const cors = require('cors');
const dotenv = require('dotenv');
const bodyParser = require('body-parser');
const config = require('./config');
const authRoutes = require('./routes/auth-routes.js');
const resumeRoutes = require('./routes/resume-routes.js');
const reportRoutes = require('./routes/report-routes.js');

//dotenv
dotenv.config();

//connect MongoDB
const mongoose = require('mongoose');
mongoose.connect(process.env.DB_URL);
let db = mongoose.connection;

db.on('error', console.error.bind(console, 'Database connect Error!'));
db.once ('open', () => {
    console.log ('Database is Connected')
});

const app = express();

//JSON parser
app.use(express.json());
app.use(cors());
app.use(bodyParser.json());

//Routes
app.use('/api/users', authRoutes.routes);
app.use('/api', resumeRoutes.routes);
app.use('/api', reportRoutes.routes);


app.listen(config.port, () => console.log('Server Up and Running on url http://localhost:' + config.port));
