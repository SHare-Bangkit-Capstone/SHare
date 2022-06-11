'use strict';

const Users = require('../models/user');
const bcrypt = require('bcrypt')
//const { registerValidation } = require ('../validation');

const Register = async (req, res, next) => {

    //Validasi
    //const { error } = registerValidation(req.body);
    //if (error) return res.status(400).send(error.details[0].message);
    
    //Checking email exist
    const EmailExist = await Users.findOne({email:req.body.email});
    if(EmailExist) return res.status(400).send('Email sudah digunakan');

    //Hash Password
    const salt = await bcrypt.genSalt(10);
    const hashedPassword = await bcrypt.hash(req.body.password, salt);

    const user = new Users({
        name: req.body.name,
        email: req.body.email,
        password: hashedPassword,
        age: req.body.age,
        gender: req.body.gender,
        phoneNumber: req.body.phoneNumber
    });
    try {
        const saveUser = await user.save();
        res.send({ user: user.id });
    } catch (error) {
        res.status(400).send(error.message);
    }
};

const Login = async (req, res, next) => {

    //Validasi
    //const { error } = registerValidation(req.body);
    //if (error) return res.status(400).send(error.details[0].message);
    
    //Checking email exist
    const emailExist = await Users.findOne({email:req.body.email});
    if(!emailExist) return res.status(400).send('Email tidak ditemukan atau salah');

    //Hash Password
    const validPass = await bcrypt.compare(req.body.password, user.password);
    if(!validPass) return res.status(400).send('Password salah');
    
    res.send ('Logged in!');

};

module.exports = { Register, Login }