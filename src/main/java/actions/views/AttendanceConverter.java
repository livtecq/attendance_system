package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Attendance;

/**
 * 勤怠データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class AttendanceConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param av AttendanceViewのインスタンス
     * @return Attendanceのインスタンス
     */
    public static Attendance toModel(AttendanceView av) {
        return new Attendance(
                av.getId(),
                EmployeeConverter.toModel(av.getEmployee()),
                av.getAttendanceDate(),
                av.getBodyTemperature(),
                av.getTimeIn(),
                av.getTimeOut(),
                av.getContent(),
                av.getCreatedAt(),
                av.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param a Attendanceのインスタンス
     * @return AttendanceViewのインスタンス
     */
    public static AttendanceView toView(Attendance a) {

        if (a == null) {
            return null;
        }

        return new AttendanceView(
                a.getId(),
                EmployeeConverter.toView(a.getEmployee()),
                a.getAttendanceDate(),
                a.getBodyTemperature(),
                a.getTimeIn(),
                a.getTimeOut(),
                a.getContent(),
                a.getCreatedAt(),
                a.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<AttendanceView> toViewList(List<Attendance> list) {
        List<AttendanceView> evs = new ArrayList<>();

        for (Attendance a : list) {
            evs.add(toView(a));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param a DTOモデル(コピー先)
     * @param av Viewモデル(コピー元)
     */
    public static void copyViewToModel(Attendance a, AttendanceView av) {
        a.setId(av.getId());
        a.setEmployee(EmployeeConverter.toModel(av.getEmployee()));
        a.setAttendanceDate(av.getAttendanceDate());
        a.setBodyTemperature(av.getBodyTemperature());
        a.setTimeIn(av.getTimeIn());
        a.setTimeOut(av.getTimeOut());
        a.setContent(av.getContent());
        a.setCreatedAt(av.getCreatedAt());
        a.setUpdatedAt(av.getUpdatedAt());

    }

    /**
     * DTOモデルの全フィールドの内容をViewモデルのフィールドにコピーする
     * @param a DTOモデル(コピー元)
     * @param av Viewモデル(コピー先)
     */
    public static void copyModelToView(Attendance a, AttendanceView av) {
        av.setId(a.getId());
        av.setEmployee(EmployeeConverter.toView(a.getEmployee()));
        av.setAttendanceDate(a.getAttendanceDate());
        av.setBodyTemperature(a.getBodyTemperature());
        av.setTimeIn(a.getTimeIn());
        av.setTimeOut(a.getTimeOut());
        av.setCreatedAt(a.getCreatedAt());
        av.setUpdatedAt(a.getUpdatedAt());
    }

}