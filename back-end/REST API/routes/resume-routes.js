const express = require('express');
const router = express.Router();
const { addResume, getAllResumes, getResumebyID, updateResume, deleteResume
   } = require('../controllers/ResumeController');

router.post('/resume', addResume);
router.get('/resumes', getAllResumes);
router.get('/resume/:id', getResumebyID);
router.put('/resume/:id', updateResume);
router.delete('/resume/:id', deleteResume);

module.exports = {
    routes: router
}