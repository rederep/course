package service;

import dao.impl.VisitDAOImpl;
import dao.impl.factory.DAOImplFactory;
import exception.BD.ChoosingClientEX;
import exception.BD.ChoosingSubscriptionEX;
import exception.BD.ChoosingWorkerEX;
import exception.BD.FileNotFoundBDConfigEX;
import exception.ModelNotFoundEX;
import model.Visit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VisitSrvc {
    private VisitDAOImpl visitDAO;

    public VisitSrvc() {
        visitDAO = DAOImplFactory.getVisitInstance();
    }

    public void addVisit(Visit visit) throws ChoosingSubscriptionEX, ChoosingClientEX, FileNotFoundBDConfigEX, ChoosingWorkerEX {
        try {
            visitDAO.addVisit(visit);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
        public void deleteVisit (int visitID) throws FileNotFoundBDConfigEX {

            try {
                visitDAO.deleteVisit(visitID);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteVisitByClientID (int clientID) throws FileNotFoundBDConfigEX {
            try {
                visitDAO.deleteVisitByClientID(clientID);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void updateVisit (Visit visit)throws ChoosingSubscriptionEX, ChoosingClientEX, FileNotFoundBDConfigEX, ChoosingWorkerEX{
            try {
                visitDAO.updateVisit(visit);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Visit getVisit (int visitID) throws FileNotFoundBDConfigEX, ModelNotFoundEX {
        Visit visit=null;
            try {
                visit=visitDAO.getVisit(visitID);
            } catch (NullPointerException e) {
                throw new ModelNotFoundEX("Visit");
            }  catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return visit;
        }

        public List<Visit> getAllVisitsID ()  throws FileNotFoundBDConfigEX {
        List<Visit> visitList = null;
            try {
                visitList=visitDAO.getAllVisitsID();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return visitList;
        }


        public List<Visit> getAllVisitsByClientID (int clientId) throws FileNotFoundBDConfigEX{
            List<Visit> visitList = null;
            try {
                visitList=visitDAO.getAllVisitsByClientID(clientId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return visitList;
        }

        public List<Visit> getAllVisitsByWorkerID (int workerId) throws FileNotFoundBDConfigEX{
            List<Visit> visitList = null;
            try {
                visitList=visitDAO.getAllVisitsByWorkerID(workerId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return visitList;
        }

        public List<Visit> getAllVisitsBySubsID (int subscriptionId) throws FileNotFoundBDConfigEX{
            List<Visit> visitList = null;
            try {
                visitList=visitDAO.getAllVisitsBySubsID(subscriptionId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return visitList;
        }
    }
