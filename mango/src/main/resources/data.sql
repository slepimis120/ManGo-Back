INSERT INTO USERS (type, name, surname, profilepicture, telephonenumber, email, address, password, blocked)
VALUES ('DRIVER', 'Momir', 'Momirovski', 'slikalink', '+2318302845', 'momir@gmail.com', 'Žarka Zrenjanina 6', '$2a$12$uP203LOQlxC1ogH74JWdP.iGxobvImrEu2CSROTSiyoDnVmvnIRoy', false),
       ('DRIVER', 'Marina', 'Marinara', 'dhajsoda', '+32198312321', 'marinara@gmail.com', 'Žitni Trg 8', '$2a$12$cxicWxEAHet6C4X3uC/Jvuw11UZPzPpDZQZ.VdvNmMBt7VwNWLD2K', false),
       ('DRIVER', 'Adam', 'Driver', 'adamdriverslika', '+8372917233', 'adamdriver@gmail.com', 'Vršačka 5', '$2a$12$2R7Jfri8eWFanFVXGZQv0OXtcW6bj/A3ZSTUWdNWIbwi.7oSQL4GK', false),
       ('DRIVER', 'Ansel', 'Elgort', 'anselslika', '+3827129312', 'jasambabydriver@gmail.com', 'Futoški put 8', '$2a$12$YUqVzEmMwW24KgHIdp6y9.GfW9oBOfC4qtgavmWYlMLlYEDoeIn5y', false);



INSERT INTO USERS (TYPE, NAME, SURNAME, PROFILEPICTURE, TELEPHONENUMBER, EMAIL, ADDRESS, PASSWORD, BLOCKED)
VALUES ('PASSENGER','Marko', 'Markov', 'linkOdSlikee', '+381694422334', 'marko@gmail.com', 'Bulevar Oslobođenja 3', '$2a$12$2Alb2iGAxZMZrDhv.dy/c.AugR6jIc6oatS1faGwPp4snLUt46u5m', false),
       ('PASSENGER','Milica', 'Milicevic', 'slikolink', '+381673829374', 'milica@gmail.com', 'Bulevar Cara Lazara 5', '$2a$12$PKKEkuSUTFEewKxFlBKgr.NjxQ7TgupPw/POF1Oxj8janOdjUGHZW', false),
       ('PASSENGER', 'Merak', 'Meraklović', 'liiknic', '+39287726313', 'meraklovski@gmail.com', 'Maksima Gorkog 2', '$2a$12$tYBoA1BTB5SqH6kcMazv9.MSvcF/zkITHGXHxRLUa29wurJPrqLbm', false),
       ('PASSENGER', 'Nemanja', 'Šimšić', 'link', '+381695511006', 'slepimis120@gmail.com', 'Miše Dimitrijevića 45', '$2a$12$OJZ7tFZCB0BQEwtB1qHEdOEBixlIINYvNxRrcNJzZcysAofkAnlDC', false),
       ('PASSENGER', 'Aleksandra', 'Balažević', 'slika', '+3937827612', 'aleksandrab@gmail.com', 'Bulevar despota Stefana 5А', '$2a$12$HJ5kBOLGf9CZrAeOWOq85.7jOUiCGBWXYbw99jGBaxed6U1EyOSdy', false),
       ('PASSENGER', 'Mina', 'Slanina', 'slikamineslanine', '+312731231', 'minaslanina@gmail.com', 'Heroja Pinkija 54', '$2a$12$nqnuJrjnccwhYkvWAErUhegH.wOUS1kvVhDWH9ghTVwKK6VJC1Us2', false),
       ('PASSENGER', 'Mila', 'Miloševski', 'mila', '+382719321', 'milamilosevski@yahoo.com', 'Mitrovačka 37', '$2a$12$HyqaJxFBRSFvFDNBjrwOqeHJiDvKl/./vZfY07Ob5Sp4o0f8UI.ui', false),
       ('PASSENGER', 'Miloš', 'Obrenović', 'milos', '+383726123', 'milosobrenovic@hotmail.com', 'Šarplaninska 30', '$2a$12$PcWXgpagykNEhjS0JvXG2OM5v8GDrnoVKPLaIC0bhSYukq5.Ig.OC', false);

INSERT INTO ACTIVATION (PASSENGERID, SENDDATE, ISACTIVATED)
VALUES (5, '2023-04-01T04:49:27Z', true),
       (6, '2023-04-01T04:49:27Z', true),
       (7, '2023-04-01T04:49:27Z', true),
       (8, '2023-04-01T04:49:27Z', true),
       (9, '2023-04-01T04:49:27Z', true),
       (10, '2023-04-01T04:49:27Z', true);


INSERT INTO DRIVER_DOCUMENT (NAME, DOCUMENTIMAGE, DRIVERID)
VALUES ('MOMIR', 'linkSlika', 1),
       ('MARINA', 'marinnaa', 2),
       ('ADAM', 'linkAdama', 3),
       ('ANSEL', 'anseLink', 4);



INSERT INTO LOCATION (ADDRESS, LATITUDE, LONGITUDE)
VALUES ('Janka Čmelika 23', 45.257030, 19.813430),
       ('Miše Dimitrijevića 43', 45.243832, 19.830820),
       ('Faculty of Technical Sciences', 45.246520, 19.851710),
       ('Srpsko Narodno Pozorište', 45.255359, 19.843049),
       ('Autobuska Stanica Novi Sad', 45.264706, 19.828317),
       ('Lazino Tele', 45.256873, 19.8451709);



INSERT INTO VEHICLE (DRIVERID, VEHICLETYPE, MODEL, LICENSENUMBER, CURRENTLOCATION, PASSENGERSEATS, BABYTRANSPORT, PETTRANSPORT)
VALUES (1, 'STANDARD', 'Golf 2', 'NS021NS', 1, 5, false, false),
       (2, 'VAN', 'Golf 22', 'ZR234OD', 2, 8, false, false);



INSERT INTO NOTE (MESSAGE, DATE, USERID)
VALUES ('Hey Yo Hey Yo Hey Yo', '2023-04-01T04:49:27Z', 1),
       ('Yeah Hey Yeah Hey Yeah Hey', '2023-04-01T04:50:27Z', 3);



INSERT INTO RIDE (STARTTIME, ENDTIME, TOTALCOST, DRIVER, ESTIMATEDTIMEINMINUTES, VEHICLETYPE, BABYTRANSPORT, PETTRANSPORT, STATUS)
VALUES ('2023-01-14T04:50:15Z', null, 530, null, 15, 'STANDARD', false, false, 'pending'),
       ('2022-12-10T13:35:49Z', '2022-12-12T04:50:09Z', 350, 2, 30, 'VAN', false, false, 'finished'),
       ('2022-12-28T22:20:39Z', '2022-12-28T23:50:08Z', 680, 1, 90, 'STANDARD', false, false, 'finished'),
       ('2010-05-07T08:11:11Z', '2010-05-07T04:50:05Z', 700, 2, 60, 'VAN', false, false, 'rejected');



INSERT INTO REJECTION (REASON, TIMEOFREJECTION, RIDEID)
VALUES ('Ne ide mi se do Miše Dimitrijevića', '2010-05-07T04:50:05Z', 4);



INSERT INTO RIDE_PASSENGERS (RIDES_ID, PASSENGERS_ID)
VALUES(1, 5),
      (2, 5),
      (2, 6),
      (3, 7),
      (3, 8),
      (4, 9),
      (4, 10);



INSERT INTO RIDE_LOCATION (DEPARTURE, DESTINATION, RIDEID)
VALUES (1, 2, 1),
       (2, 1, 2),
       (3, 4, 3),
       (5, 6, 4);



INSERT INTO REVIEW (RIDEID, RATING, COMMENT, PASSENGERID, REVIEWTYPE)
VALUES (2, 5, 'Sjajna voznja', 5, 'DRIVER'),
       (2, 4, 'Pa okej auto šta ja znam', 5, 'VEHICLE'),
       (2, 3, 'Dosadila vožnja', 6, 'DRIVER'),
       (2, 4, 'Okej auto', 6, 'VEHICLE');



INSERT INTO USER_MESSAGE(TIMEOFSENDING, SENDERID, RECEIVERID, MESSAGE, TYPE, RIDEID)
VALUES ('2023-01-14T04:50:15Z', 1, 9, 'Hej Hej', 'RIDE', 1);



INSERT INTO PANIC(USERID, RIDE, TIME, REASON)
VALUES(1, 1, '2023-01-14T04:50:15Z', 'Mnogo je dosadan vozac :/');




INSERT INTO WORK_HOUR(STARTTIME, ENDTIME, DRIVERID)
VALUES('2023-01-14T04:50:15Z', '2023-01-14T06:50:15Z', 1),
    ('2023-01-15T05:50:15Z', '2023-01-15T13:50:15Z', 1),
    ('2023-01-15T14:50:15Z', '2023-01-15T22:50:15Z', 2);





INSERT INTO FAVORITE_LOCATIONS(FAVORITENAME, VEHICLETYPE, BABYTRANSPORT, PETTRANSPORT, ISDELETED)
VALUES('Kuća - Poso', 'STANDARD', true, false, false),
      ('Faks', 'STANDARD', false, false, false);




INSERT INTO FAVORITE_LOCATIONS_LOCATIONS(FAVORITE_LOCATIONS_ID, LOCATIONS_ID)
VALUES(1, 1),
      (2, 2);




INSERT INTO FAVORITE_LOCATIONS_PASSENGERS(PASSENGERS_ID, FAVORITE_LOCATIONS_ID)
VALUES(5, 1),
      (5, 2);
