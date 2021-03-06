const Joi = require('@hapi/joi');

//Register Validasi
const registerValidation = data => {
    const schema = {
    name: Joi.string().min(6).required(),
    email: Joi.string().min(6).required().email(),
    password: Joi.string().min(6).required()
    };
    return Joi.validate(data, schema)
};

module.exports.registerValidation = registerValidation;