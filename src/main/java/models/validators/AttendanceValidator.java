package models.validators;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import actions.views.AttendanceView;
import constants.MessageConst;

/**
 * 勤怠インスタンスに設定されている値のバリデーションを行うクラス
 */
public class AttendanceValidator {

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


        return errors;
    }

    /**
     * 出勤に入力値があるかチェックし、入力値がなければエラーメッセージを返却
     * @param timeIn 出勤
     * @return エラーメッセージ
     */
    private static String validateTimeIn(LocalTime timeIn) {
        if (timeIn == null) {
            return MessageConst.E_NOTIMEINUP.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

     /**
      * 退勤に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
      * @param timeOut 退勤
      * @return エラーメッセージ
      */
     private static String validateTimeOut(LocalTime timeOut) {
         if (timeOut == null) {
             return MessageConst.E_NOTIMEOUTUP.getMessage();
         }

         //入力値がある場合は空文字を返却
         return "";
     }


}