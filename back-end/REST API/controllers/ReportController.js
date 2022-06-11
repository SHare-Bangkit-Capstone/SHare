'use strict';

const Reports = require('../models/report');
//const { registerValidation } = require ('../validation');

const addReport = async (req, res, next) => {

    //Validasi
    //const { error } = registerValidation(req.body);
    //if (error) return res.status(400).send(error.details[0].message);

    const report = new Reports({
        userReport: req.body.userReport,
        titleReport: req.body.titleReport,
        mainReport: req.body.mainReport
    });
    try {
        const saveReport= await report.save();
        res.send({user: report._id});
    } catch (error) {
        res.status(400).send(error.message);
    }
};

const getAllReports = async (req, res, next) => {
    try {
        const findAllReport= await Reports.find();
        res.send(findAllReport);
    } catch (error) {
        res.status(400).send('Data report tidak ditemukan');
    }
};

const getReportbyID = async (req, res, next) => {
    try {
        const findReportbyID= await Reports.findById(req.params.id)
        .then(userFound => {
            if (!userFound) { return res.status(404).send('Data report dengan ID tersebut tidak ditemukan'); }
            return res.status(200).json(userFound)
        });
    } catch (error) {
        res.status(400).send('Data report tidak ditemukan');
    }
};

const updateReport = async (req, res, next) => {
    try {
        const updateReport= await Reports.updateOne({_id: req.params.id},
        {
            userReport: req.body.userReport,
            titleReport: req.body.titleReport,
            mainReport: req.body.mainReport
        });
        res.status(200).send('Data report berhasil diupdate');
    } catch (error) {
        res.status(400).send('Data report tidak ditemukan');
    }
};

const deleteReport = async (req, res, next) => {
    try {
        const deleteReport= await Reports.deleteOne({_id: req.params.id});
        res.status(200).send('Data report berhasil dihapus')
    } catch (error) {
        res.status(400).send('Data report tidak ditemukan');
    }
};

module.exports = {
    addReport,
    getAllReports,
    getReportbyID,
    updateReport,
    deleteReport
}