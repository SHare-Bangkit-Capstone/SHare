const express = require('express');
const router = express.Router();
const { Register, Login
   } = require('../controllers/AuthController');

router.post('/register', Register);
router.post('/login', Login);


module.exports = {
    routes: router
}