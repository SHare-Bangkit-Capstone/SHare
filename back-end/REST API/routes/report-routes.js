const express = require('express');
const router = express.Router();
const { addReport, getAllReports, getReportbyID, updateReport, deleteReport
   } = require('../controllers/ReportController');

router.post('/report', addReport);
router.get('/reports', getAllReports);
router.get('/report/:id', getReportbyID);
router.put('/report/:id', updateReport); 
router.delete('/report/:id', deleteReport);

module.exports = {
    routes: router
}