package models.validators;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.AttendanceView;
import constants.MessageConst;

/**
 * 勤怠インスタンスに設定されている値のバリデーションを行うクラス
 */
public class TopValidator {

    /**
     * 勤怠インスタンスの各項目についてバリデーションを行う
     * @param av 勤怠インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(AttendanceView av) {
        List<String> errors = new ArrayList<String>();

         //出勤のチェック
         String timeInError = validateTimeIn(av.getTimeIn());
         if (!timeInError.equals("")) {
             errors.add(timeInError);
         }

         //退勤のチェック
         String timeOutError = validateTimeOut(av.getTimeOut());
         if (!timeOutError.equals("")) {
             errors.add(timeOutError);
         }

         //体温のチェック
         String bodyTemperatureError = validateBodyTemperature(av.getBodyTemperature());
         if (!bodyTemperatureError.equals("")) {
             errors.add(bodyTemperatureError);
         }

        return errors;
    }

    /**
     * 本日の出勤があるかをチェックし、登録があればエラーメッセージを返却
     * @param timeIn 出勤
     * @return エラーメッセージ
     */
    private static String validateTimeIn(LocalTime timeIn) {
        if (timeIn != null) {
            return MessageConst.E_NOTIMEIN.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

     /**
      * 既に退勤時間の登録があるかをチェックし、登録があればエラーメッセージを返却
      * @param timeOut 退勤
      * @return エラーメッセージ
      */
     private static String validateTimeOut(LocalTime timeOut) {
         if (timeOut != null) {
             return MessageConst.E_NOTIMEOUT.getMessage();
         }

         //入力値がある場合は空文字を返却
         return "";
     }

     /**
      * 既に本日の体温の登録があるかチェックし、登録があればエラーメッセージを返却
      * @param bodyTemperature 体温
      * @return エラーメッセージ
      */
     private static String validateBodyTemperature(String bodyTemperature) {
         if (bodyTemperature != null) {
             return MessageConst.E_NOBODY.getMessage();
         }

         //入力値がある場合は空文字を返却
         return "";
     }
}