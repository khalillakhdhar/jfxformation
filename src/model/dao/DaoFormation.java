package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import classlist.Formation;

public interface DaoFormation {
void createFormation();
void updateFormation(int id);
void deleteFormation(int id);
ResultSet listFormation() throws SQLException;
Formation findFormation(String titre) throws SQLException;
Formation searchFormation(int id) throws SQLException;
}
