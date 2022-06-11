'use strict';

const Resumes = require('../models/resume');
//const { registerValidation } = require ('../validation');

const addResume = async (req, res, next) => {

    //Validasi
    //const { error } = registerValidation(req.body);
    //if (error) return res.status(400).send(error.details[0].message);

    const resume = new Resumes({
        userResume: req.body.userResume,
        titleResume: req.body.titleResume,
        mainResume: req.body.mainResume,
        createdResumeBy: req.body.createdResumeBy
    });
    try {
        const saveResume = await resume.save();
        res.send({user: resume._id});
    } catch (error) {
        res.status(400).send(error.message);
    }
};

const getAllResumes = async (req, res, next) => {
    try {
        const findAllResumes= await Resumes.find();
        res.send(findAllResumes);
    } catch (error) {
        res.status(400).send('Data resume tidak ditemukan');
    }
};

const getResumebyID = async (req, res, next) => {
    try {
        const findResumebyID= await Resumes.findById(req.params.id)
        .then(userFound => {
            if (!userFound) { return res.status(404).send('Data resume dengan ID tersebut tidak ditemukan'); }
            return res.status(200).json(userFound)
        });
    } catch (error) {
        res.status(400).send('Data resume tidak ditemukan');
    }
};

const updateResume = async (req, res, next) => {
    try {
        const updateResume= await Resumes.updateOne({_id: req.params.id},
        {
            userResume: req.body.userResume,
            titleResume: req.body.titleResume,
            mainResume: req.body.mainResume,
            createdResumeBy: req.body.createdResumeBy
        });
        res.status(200).send('Data resume berhasil diupdate');
    } catch (error) {
        res.status(400).send('Data resume tidak ditemukan');
    }
};

const deleteResume = async (req, res, next) => {
    try {
        const deleteResume= await Resumes.deleteOne({_id: req.params.id});
        res.status(200).send('Data resume berhasil dihapus')
    } catch (error) {
        res.status(400).send('Data resume tidak ditemukan');
    }
};

module.exports = {
    addResume,
    getAllResumes,
    getResumebyID,
    updateResume,
    deleteResume
}