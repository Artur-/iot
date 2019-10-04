package org.artur.iot.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.artur.iot.data.Floorplan;
import org.artur.iot.data.Room;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitDB {

    @Autowired
    private RoomRepository repo;

    @PostConstruct
    public void init() {
        // Initialize db with some demo data
        repo.saveAll(getInitialRoomdata());
        repo.flush();
        LoggerFactory.getLogger(getClass()).info("Initialized database with {} rooms", repo.count());
    }

    public static List<Room> getInitialRoomdata() {
        List<Room> rooms = new ArrayList<Room>();
        {
            Room room = new Room();
            room.setSensorId("698af98f");
            room.setRoom("Living Room");
            room.setTarget(21);
            room.setTemperature(24.6);
            room.setPower(1200);
            room.setHeating(false);
            Double[] history = new Double[] { 24.0, 23.9, 23.8, 23.6, 23.5, 23.4, 23.3, 23.2, 23.1, 23.0, 23.2, 23.3,
                    23.4, 23.3, 23.0, 23.5, 23.4, 23.6, 24.2, 24.4, 24.4, 23.6, 24.0, 24.6, 24.6 };
            List<Double> historyList = new ArrayList<>();
            Collections.addAll(historyList, history);
            room.setHistory(historyList);
            Floorplan floorplan = new Floorplan();
            floorplan.setX(94.4);
            floorplan.setY(48.4);
            floorplan.setRoomId("living-room");
            room.setFloorplan(floorplan);
            rooms.add(room);
        }
        {
            Room room = new Room();
            room.setSensorId("59af20a0");
            room.setRoom("Bedroom");
            room.setTarget(25);
            room.setTemperature(22.09289236837965);
            room.setPower(600);
            room.setHeating(true);
            Double[] history = new Double[] { 22.0, 21.96671062916677, 22.218627748356568, 22.315976204126546,
                    22.037521203543584, 21.783453811125693, 21.80907304341113, 21.6507567460157, 21.675663295450047,
                    21.710763750780888, 21.84102587551224, 22.058914163079653, 22.223683127475855, 22.51730334780377,
                    22.62966070606003, 22.359639402839775, 22.171148351235637, 22.422876541718818, 22.379780449446802,
                    22.331987410032024, 22.18477977139349, 22.2293855497442, 22.08247499740029, 22.14018248902325,
                    21.924222554922476, 21.974223993246348, 22.262973054093138, 22.09289236837965 };
            List<Double> historyList = new ArrayList<>();
            Collections.addAll(historyList, history);
            room.setHistory(historyList);
            Floorplan floorplan = new Floorplan();
            floorplan.setX(94.4);
            floorplan.setY(93.2);
            floorplan.setRoomId("bedroom");
            room.setFloorplan(floorplan);
            rooms.add(room);
        }
        {
            Room room = new Room();
            room.setSensorId("935a0230");
            room.setRoom("Office");
            room.setTarget(20);
            room.setTemperature(19.13452209312482);
            room.setPower(400);
            room.setHeating(true);
            Double[] history = new Double[] { 19.0, 18.72817116684312, 18.549439070289477, 18.374035549797554,
                    18.664220523729224, 18.896644533450267, 19.122748385566457, 18.824293982800935, 18.98240549350083,
                    19.13831736864842, 18.86363320488054, 18.984865153076164, 18.92731774120241, 19.030384697974814,
                    19.025899093048444, 18.7949053060223, 18.838692829135812, 18.80191909968981, 18.57168457186385,
                    18.79907599716696, 18.905284487440653, 19.074615812775107, 19.117375443414286, 18.85928186910918,
                    18.92736170693772, 18.837355200530123, 18.86538022995704, 18.8903629932542, 18.797522108230908,
                    19.028787690488343, 19.13452209312482 };
            List<Double> historyList = new ArrayList<>();
            Collections.addAll(historyList, history);
            room.setHistory(historyList);
            Floorplan floorplan = new Floorplan();
            floorplan.setX(2);
            floorplan.setY(48);
            floorplan.setRoomId("office");
            room.setFloorplan(floorplan);
            rooms.add(room);
        }
        {
            Room room = new Room();
            room.setSensorId("11022aff");
            room.setRoom("Hall");
            room.setTarget(21);
            room.setTemperature(30.911688075975707);
            room.setPower(400);
            room.setHeating(false);
            Double[] history = new Double[] { 29.0, 28.75663032594999, 28.962322954805227, 28.871338104084543,
                    28.888344098723504, 29.177858746309237, 29.018246730431184, 28.943633084840204, 29.216022188299497,
                    29.491419778541648, 29.74275134030435, 29.995589519781266, 29.962042197734156, 30.089301083017045,
                    30.282899144856916, 30.232141669242544, 30.35500129382464, 30.278044461719475, 30.091679562300474,
                    30.307059494647937, 30.40172668607298, 30.68250544103281, 30.723246394079734, 30.971474435702593,
                    31.17584922731008, 31.43509837665023, 31.40137222914464, 31.115385133696446, 31.165901921404078,
                    31.10753941118982, 30.911688075975707 };
            List<Double> historyList = new ArrayList<>();
            Collections.addAll(historyList, history);
            room.setHistory(historyList);
            Floorplan floorplan = new Floorplan();
            floorplan.setX(33.1);
            floorplan.setY(55.3);
            floorplan.setRoomId("hall");
            room.setFloorplan(floorplan);
            rooms.add(room);
        }
        return rooms;

    }

}