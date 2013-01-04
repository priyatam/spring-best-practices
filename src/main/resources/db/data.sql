INSERT into Address(addrLine1,addrLine2,city,state,zip,type) VALUES('100 memorial dr','','cambridge','MA','02139','HOME');
INSERT into Address(addrLine1,addrLine2,city,state,zip,type) VALUES('100 cambridge st','Apt 1','cambridge','MA','02141','HOME');
INSERT into Address(addrLine1,addrLine2,city,state,zip,type) VALUES('100 Mass Ave','Ste 1','Boston','MA','02116','WORK');

INSERT into Vehicle(vin,make,model,year,plateNum,owner_type) VALUES('vin-1','bmw','335xi',2005,'AMX123','OWNED');
INSERT into Vehicle(vin,make,model,year,plateNum,owner_type) VALUES('vin-2','honda','civic',2010,'768DGZ','LEASED');
INSERT into Vehicle(vin,make,model,year,plateNum,owner_type) VALUES('vin-3','toyota','camry',2004,'998XUY','LEASED');

INSERT into Driver(addressId,licenseNum,licenseExpiryDate,firstName,lastName,birthDate,gender,email,phone,occupation,firstLicenseAtAge,married)
    VALUES(1,'lic-1','2014-10-10','Ernest','Hemmingway','1899-11-30','MALE','ehemmingway@writers.com','6177189876','writer',22,FALSE);
INSERT into Driver(addressId,licenseNum,licenseExpiryDate,firstName,lastName,birthDate,gender,email,phone,occupation,firstLicenseAtAge,married)
    VALUES(2,'lic-2','2016-10-10','Franz','Kafka','1849-11-30','MALE','fkafka@writers.com','9187189876','writer',12,FALSE);
    
INSERT INTO DrivingHistory(driverId, annualMileage,primaryOperator) VALUES(1,15000,TRUE);
INSERT INTO DrivingHistory(driverId, annualMileage,primaryOperator) VALUES(1,25000,FALSE);

INSERT into Accident(drivingHistoryId, name,incidentTime,thirdParyOffence,type) VALUES(1, 'drunken-bumper1','2012-10-21 12:23:22',TRUE,'MINOR');
INSERT into Accident(drivingHistoryId, name,incidentTime,thirdParyOffence,type) VALUES(1, 'head-on123','2012-10-21 12:23:22',TRUE,'COLLISSION');

INSERT INTO Policy(policyNum,effectiveDate,expiryDate,term,company,state,quote,agency) VALUES('pol-1','2014-01-01','2015-01-01',3,'commerce','MA','1033','commerce one');
INSERT INTO Policy(policyNum,effectiveDate,expiryDate,term,company,state,quote,agency) VALUES('pol-2','2013-02-01','2014-01-01',2,'geico','MA','1633','agency one');

