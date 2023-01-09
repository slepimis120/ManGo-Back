INSERT INTO USERS (type, name, surname, profilepicture, telephonenumber, email, address, password, blocked)
VALUES ('DRIVER', 'Momir', 'Momirovski', 'slikalink', '+2318302845', 'momir@gmail.com', 'Žarka Zrenjanina 6', 'mmomirko', false),
       ('DRIVER', 'Marina', 'Marinara', 'dhajsoda', '+32198312321', 'marinara@gmail.com', 'Žitni Trg 8', 'mmarinara', false),
       ('DRIVER', 'Adam', 'Driver', 'adamdriverslika', '+8372917233', 'adamdriver@gmail.com', 'Vršačka 5', 'jasamdriver', false),
       ('DRIVER', 'Ansel', 'Elgort', 'anselslika', '+3827129312', 'jasambabydriver@gmail.com', 'Futoški put 8', 'babydriver', false);



INSERT INTO USERS (TYPE, NAME, SURNAME, PROFILEPICTURE, TELEPHONENUMBER, EMAIL, ADDRESS, PASSWORD, BLOCKED)
VALUES ('PASSENGER','Marko', 'Markov', 'linkOdSlikee', '+381694422334', 'marko@gmail.com', 'Bulevar Oslobođenja 3', 'mmarko', false),
       ('PASSENGER','Milica', 'Milicevic', 'slikolink', '+381673829374', 'milica@gmail.com', 'Bulevar Cara Lazara 5', 'mmilica', false),
       ('PASSENGER', 'Merak', 'Meraklović', 'liiknic', '+39287726313', 'meraklovski@gmail.com', 'Maksima Gorkog 2', 'mmerak', false),
       ('PASSENGER', 'Nemanja', 'Šimšić', 'link', '+381695511006', 'slepimis120@gmail.com', 'Miše Dimitrijevića 45', 'nemanjas', false),
       ('PASSENGER', 'Aleksandra', 'Balažević', 'slika', '+3937827612', 'aleksandrab@gmail.com', 'Bulevar despota Stefana 5А', 'sakisaki', false),
       ('PASSENGER', 'Mina', 'Slanina', 'slikamineslanine', '+312731231', 'minaslanina@gmail.com', 'Heroja Pinkija 54', 'slanina', false),
       ('PASSENGER', 'Mila', 'Miloševski', 'mila', '+382719321', 'milamilosevski@yahoo.com', 'Mitrovačka 37', 'milavelika', false),
       ('PASSENGER', 'Miloš', 'Obrenović', 'milos', '+383726123', 'milosobrenovic@hotmail.com', 'Šarplaninska 30', 'mikiveliki', false);



INSERT INTO DRIVER_DOCUMENT (NAME, DOCUMENTIMAGE, DRIVERID)
VALUES ('MOMIR', 'linkSlika', 1),
       ('MARINA', 'marinnaa', 2),
       ('ADAM', 'linkAdama', 3),
       ('ANSEL', 'anseLink', 4);



INSERT INTO LOCATION (ADDRESS, LATITUDE, LONGITUDE)
VALUES ('Janka Čmelika 23', 45.257030, 19.813430),
       ('Miše Dimitrijevića 43', 45.243832, 19.830820);



INSERT INTO VEHICLE (DRIVERID, VEHICLETYPE, MODEL, LICENSENUMBER, CURRENTLOCATION, PASSENGERSEATS, BABYTRANSPORT, PETTRANSPORT)
VALUES (1, 'STANDARD', 'Golf 2', 'NS021NS', 1, 5, false, false),
       (2, 'VAN', 'Golf 22', 'ZR234OD', 2, 8, false, false);



INSERT INTO NOTE (MESSAGE, DATE, USERID)
VALUES ('Hey Yo Hey Yo Hey Yo', '2023-04-01T04:49:27Z', 1),
       ('Yeah Hey Yeah Hey Yeah Hey', '2023-04-01T04:50:27Z', 3);



INSERT INTO RIDE (STARTTIME, ENDTIME, TOTALCOST, DRIVERID, ESTIMATEDTIMEINMINUTES, VEHICLETYPE, BABYTRANSPORT, PETTRANSPORT, STATUS)
VALUES ('2023-01-14T04:50:15Z', null, null, 1, null, 'STANDARD', false, false, 'pending'),
       ('2022-12-10T13:35:49Z', '2022-12-12T04:50:09Z', 350, 2, 30, 'VAN', false, false, 'finished'),
       ('2022-12-28T22:20:39Z', '2022-12-28T23:50:08Z', 680, 1, 90, 'STANDARD', false, false, 'finished'),
       ('2010-05-07T08:11:11Z', '2010-05-07T04:50:05Z', 700, 2, 60, 'VAN', false, false, 'rejected');



INSERT INTO REJECTION (REASON, TIMEOFREJECTION, RIDEID)
VALUES ('Ne ide mi se do Miše Dimitrijevića', '2010-05-07T04:50:05Z', 4);



INSERT INTO RIDE_PASSENGERS (RIDES_ID, PASSENGERS_ID)
VALUES(1, 1),
      (2, 1),
      (2, 2);



INSERT INTO RIDE_LOCATION (DEPARTURE, DESTINATION, RIDEID)
VALUES (1, 2, 1),
       (2, 1, 2);



INSERT INTO REVIEW (RIDEID, RATING, COMMENT, PASSENGERID)
VALUES (2, 5, 'Sjajna voznja', 1),
       (2, 4, 'Pa okej auto šta ja znam', 1),
       (2, 3, 'Dosadila vožnja', 2),
       (2, 4, 'Okej auto', 2);



INSERT INTO REVIEW_OVERVIEW(DRIVERREVIEW, VEHICLEREVIEW, RIDEID)
VALUES (1, 2, 2),
       (3, 4, 2);



INSERT INTO USER_MESSAGE(TIMEOFSENDING, SENDERID, RECEIVERID, MESSAGE, TYPE, RIDEID)
VALUES ('2023-01-14T04:50:15Z', 1, 9, 'Hej Hej', 'RIDE', 1);



INSERT INTO PANIC(USERID, RIDE, TIME, REASON)
VALUES(1, 1, '2023-01-14T04:50:15Z', 'Mnogo je dosadan vozac :/');




INSERT INTO WORK_HOUR(STARTTIME, ENDTIME, DRIVERID)
VALUES('2023-01-14T04:50:15Z', '2023-01-14T06:50:15Z', 1);

