import React from "react";
import { Redirect } from "react-router-dom";
import sessionRoutes from "./views/sessions/SessionRoutes";
import dashboardRoutes from "./views/dashboard/DashboardRoutes";
import userRoutes from "./views/User/UserRoutes";
import roleRoutes from "./views/Role/RoleRoutes";
import ConstantList from "./appConfig";
import patientRoutes from "./views/Patient/PatientRoutes";
import drugStoreRoutes from "./views/DrugStore/DrugStoreRoutes";
import medication from "./views/Medication/MedicationRoutes";
import regimenRoutes from "./views/Regimen/RegimenRoutes";
import regimenPhaseRoutes from "./views/RegimenPhase/RegimenPhaseRoutes";
import AdministrativeUnitRoutes from "./views/AdministrativeUnit/AdministrativeUnitRoutes";
import ethnicsRoutes from "./views/Ethnics/EthnicsRoutes";
import conceptRoutes from "./views/Concept/ConceptRoutes";
import HealthOganizationUnitRoutes from "./views/HealthOganizationUnit/HealthOganizationUnitRoutes";
import countryRoutes from "./views/Country/CountryRoutes";
import religionRoutes from "./views/Religion/ReligionRoutes";
import professionRoutes from "./views/Profession/ProfessionRoutes";
import conceptLabTestRoutes from "./views/ConceptLabTest/ConceptLabTestRoutes";
import conceptDiagnosticRoutes from "./views/ConceptDiagnostic/ConceptDiagnosticRoutes";
import conceptSymptomRoutes from "./views/ConceptSymptom/ConceptSymptomRoutes";
import conceptISFRoutes from "./views/ConceptISF/ConceptISFRoutes";
import staffRoute from "./views/Staff/StaffRoutes";
import specimenRoute from "./views/Specimen/SpecimenRoutes";
import examineRequestRoute from "./views/ExamineRequest/ExamineRequestListRoutes";
import serviceRequestFormRoutes from "./views/ServiceRequestForm/ServiceRequestFormRoutes";
import healthDepartmentRoutes from "./views/HealthDepartment/HealthDepartmentRoutes";
import stockKeepingUnitRoutes from "./views/StockKeepingUnit/StockKeepingUnitRoutes";
import specimenTypeRoutes from "./views/SpecimenType/SpecimenTypeRoutes";
import sourceRoutes from "./views/Source/SourceRoutes";
import regionRoutes from "./views/Region/RegionRoutes";
import medicationName from "./views/MedicationName/MedicationNameRoutes";
import pulmonaryTBType from "./views/PulmonaryTBType/PulmonaryTBTypeRoutes";
import systemConfigRoutes from "./views/SystemConfig/SystemConfigRoutes";
import regimenTestConceptRoutes from './views/RegimenTestConcept/RegimenTestConceptRoutes';
import dictionarySystemRoutes from './views/DictionarySystem/DictionarySystemRoutes';
import pageLayoutRoutes from './views/page-layouts/PageLayoutRoutees';
import reportRoutes from './views/Report/ReportRoutes';
import duplicatePatientRoutes from './views/DuplicatePatient/DuplicatePatientRoutes';
import report01Routes from './views/Report/ETB/ImportReport/Report01/Report01Routes';
import report02Routes from './views/Report/ETB/ImportReport/Report02/Report02Routes';
import report03Routes from './views/Report/ETB/ImportReport/Report03/Report03Routes';
import report04Routes from './views/Report/ETB/ImportReport/Report04/Report04Routes';
import report05Routes from './views/Report/ETB/ImportReport/Report05/Report05Routes';
import report06Routes from './views/Report/ETB/ImportReport/Report06/Report06Routes';
import pendingTestListRoutes from "./views/PendingTestList/PendingTestListRoutes";
import patientInCompleteADSMRoutes from "./views/PatientInCompleteADSM/PatientInCompleteADSMRoutes";
import patientLateAppointmentRoutes from "./views/PatientLateAppointment/PatientLateAppointmentRoutes";
import patientDoNotTestRForLpaRoutes from "./views/PatientDoNotTestRForLpa/PatientDoNotTestRForLpaRoutes";
import patientWithoutRegimenEAndIndividualRoutes from "./views/PatientWithoutRegimenEAndIndividual/PatientWithoutRegimenEAndIndividualRoutes";
import viewReport05Routes from './views/Report/ETB/ViewReport/Report05/Report05Routes';
import viewReport02Routes from './views/Report/ETB/ViewReport/Report02/Report02Routes';
import viewReport04Routes from './views/Report/ETB/ViewReport/Report04/Report04Routes';
import patientADSM from "./views/PatientADSM/PatientADSMRoutes";
import labTestWaitingResultRoutes from './views/LabTestWaitingResult/LabTestWaitingResultRoutes';
import labTestHasResultRoutes from './views/LabTestHasResult/LabTestHasResultRoutes';
import viewReport01Routes from './views/Report/ETB/ViewReport/Report01/Report01Routes';
import patientAppointmentTodayRoutes from './views/PatientAppointmentToday/PatientAppointmentTodayRoutes';
import patientMedicationHistoryRoutes from "./views/PatientMedicationHistory/PatientMedicationHistoryRoutes";
import patientPositiveTestWithoutAntibiogramRoutes from "./views/PatientPositiveTestWithoutAntibiogram/PatientPositiveTestWithoutAntibiogramRoutes"
import exportDataRoutes from "./views/ExportData/ExportDataRoutes";
import patientReminderCardRoutes from "./views/PatientReminderCard/PatientReminderCardRoutes";
import patientFromNotificationRoutes from "./views/PatientFromNotification/PatientFromNotificationRoutes";
import medicationRecordSheetRoutes from "./views/MedicationRecordSheet/MedicationRecordSheetRoutes";
import activityLogRoutes from "./views/ActivityLog/ActivityLogRoutes";
import viewReport08Routes from './views/Report/ETB/ViewReport/Report08/Report08Routes';
import viewReport03Routes from './views/Report/ETB/ViewReport/Report03/Report03Routes';
import roleDetailRoutes from './views/RoleDetail/RoleDetailRoutes';
import administrativeUnitRefInfoRoutes from "./views/AdministrativeUnitRefInfo/AdministrativeUnitRefInfoRoutes";

const redirectRoute = [
  {
    path: ConstantList.ROOT_PATH,
    exact: true,
    component: () => <Redirect to={ConstantList.HOME_PAGE} />, //Luôn trỏ về HomePage được khai báo trong appConfig
  },
];

const errorRoute = [
  {
    // component: () => <Redirect to={ConstantList.ROOT_PATH + "session/404"} />,
    component: () => <Redirect to={ConstantList.HOME_PAGE} />
  },
];

const categoryRoutes = [
  {
    // auth: ["ROLE_ADMIN"],
    detailRole: ["administration"],
    path: ConstantList.ROOT_PATH + "category",
    children: [
      ...ethnicsRoutes,
      ...AdministrativeUnitRoutes,
      ...HealthOganizationUnitRoutes,
      ...drugStoreRoutes,
      ...medication,
      ...regimenRoutes,
      ...regimenPhaseRoutes,
      ...conceptRoutes,
      ...countryRoutes,
      ...religionRoutes,
      ...professionRoutes,
      ...conceptLabTestRoutes,
      ...conceptDiagnosticRoutes,
      ...conceptSymptomRoutes,
      ...conceptISFRoutes,
      ...specimenRoute,
      ...stockKeepingUnitRoutes,
      ...specimenTypeRoutes,
      ...sourceRoutes,
      ...regionRoutes,
      ...medicationName,
      ...pulmonaryTBType,
      ...administrativeUnitRefInfoRoutes,
      // ...storeRoutes,
      ...regimenTestConceptRoutes,
      ...dictionarySystemRoutes,
      ...serviceRequestFormRoutes,
      // ...patientADSM,
      ...patientReminderCardRoutes,
      ...staffRoute,
      ...healthDepartmentRoutes,
    ]
  },
  // tài khoản quyền tỉnh
  {
    auth: ["ROLE_PROVINCE"],
    path: ConstantList.ROOT_PATH + "category",
    children: [
      ...HealthOganizationUnitRoutes,
    ]
  },
  // tài khoản quyền đơn vị
  {
    auth: ["ROLE_ORGANIZATION"],
    path: ConstantList.ROOT_PATH + "category",
    children: [
      ...staffRoute,
      ...healthDepartmentRoutes,
    ]
  }
]

const setting = [
  {
    // auth: ["ROLE_ADMIN"],
    detailRole: ["administration"],
    path: ConstantList.ROOT_PATH + "setting",
    children: [
      ...roleRoutes,
      ...userRoutes,
      ...systemConfigRoutes,
      ...activityLogRoutes,
      ...roleDetailRoutes,
    ]
  },
]

const importReport = {
  detailRole: ["editReportLKT"],
  path: ConstantList.ROOT_PATH + "report",
  children: [
    ...report01Routes,
    ...report02Routes,
    ...report05Routes,
    // ...report03Routes,
    // ...report04Routes,
    // ...report06Routes,
  ]
}

const viewReport = {
  detailRole: ["viewReportLKT"],
  path: ConstantList.ROOT_PATH + "report",
  children: [
    ...viewReport05Routes,
    ...viewReport02Routes,
    ...viewReport04Routes,
    ...viewReport01Routes,
    ...viewReport08Routes,
    ...viewReport03Routes,
    //mockup
    ...reportRoutes,
  ]
}

const exportData = {
  // auth: [
  //   "ROLE_ADMIN", 
  //   "ROLE_NATION", 
  //   "ROLE_REGION",
  //   "ROLE_PROVINCE",
  //   "ROLE_DISTRICT",
  //   "ROLE_ORGANIZATION",
  //   "ROLE_DEPARTMENT",
  // ],
  detailRole: ["viewReportLKT"],
  path: ConstantList.ROOT_PATH + "export-data",
  children: [
    ...exportDataRoutes,
    ...medicationRecordSheetRoutes,
  ]
}

const laboratory = {
  // auth: [
  //   "ROLE_ADMIN",
  //   "ROLE_LABORATORY", 
  //   "ROLE_ORGANIZATION"
  // ],
  detailRole: ["test"],
  path: ConstantList.ROOT_PATH + "laboratory",
  children: [
    ...labTestWaitingResultRoutes,
    ...labTestHasResultRoutes,
  ]
}

const patient = {
  // auth: [
  //   "ROLE_ADMIN", 
  //   "ROLE_NATION", 
  //   "ROLE_REGION",
  //   "ROLE_PROVINCE",
  //   "ROLE_DISTRICT",
  //   "ROLE_ORGANIZATION",
  //   "ROLE_DEPARTMENT",
  //   "ROLE_LABORATORY",
  //   "ROLE_WARD",
  // ],
  detailRole: ["vewPatient", "dotThuoc"],
  children: [
    ...patientRoutes,
    ...duplicatePatientRoutes,
    ...pendingTestListRoutes,
    ...patientInCompleteADSMRoutes,
    ...patientLateAppointmentRoutes,
    ...patientDoNotTestRForLpaRoutes,
    ...patientWithoutRegimenEAndIndividualRoutes,
    ...patientAppointmentTodayRoutes,
    ...patientMedicationHistoryRoutes,
    ...patientPositiveTestWithoutAntibiogramRoutes,
    ...patientFromNotificationRoutes,
    ...patientADSM,
  ]
}

let routes = [
  ...sessionRoutes,
  ...dashboardRoutes,
  patient,
  ...redirectRoute,
  importReport,
  viewReport,
  ...categoryRoutes,
  ...examineRequestRoute,
  ...setting,
  laboratory,
  ...pageLayoutRoutes,
  exportData,
  ...errorRoute,
];

export default routes;
