const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        max:255,
        min:6
    },
    email: {
        type: String,
        required: true,
        max:255,
        min:6
    },
    password: {
        type: String,
        required: true,
        max:1024,
        min:6
    },
    age: {
        type: Number,
        required: true,
        max:255,
        min:6
    },
    gender: {
        type: String,
        required: true,
        max:255,
        min:6
    },
    phoneNumber: {
        type: String,
        required: true,
        max:255,
        min:6
    },
    date: {
        type: Date,
        required: true,
        default: Date.now
    }
});

module.exports = mongoose.model('user', userSchema);