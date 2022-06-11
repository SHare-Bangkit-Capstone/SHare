const mongoose = require('mongoose');

const reportSchema = new mongoose.Schema({
    userReport: {
        type: String,
        required: true,
        max:255,
        min:6
    },
    titleReport: {
        type: String,
        required: true,
        max:255,
        min:6
    },
    mainReport: {
        type: String,
        required: true,
        max:5120,
        min:6
    },
    date: {
        type: Date,
        required: true,
        default: Date.now
    }
});

module.exports = mongoose.model('report', reportSchema);