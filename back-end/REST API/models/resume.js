const mongoose = require('mongoose');

const resumeSchema = new mongoose.Schema({
    userResume: {
        type: String,
        required: true,
        max:255,
        min:6
    },
    titleResume: {
        type: String,
        required: true,
        max:255,
        min:6
    },
    mainResume: {
        type: String,
        required: true,
        max:5120,
        min:6
    },
    createdResumeBy: {
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

module.exports = mongoose.model('resume', resumeSchema);