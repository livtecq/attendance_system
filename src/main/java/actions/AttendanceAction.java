package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.AttendanceView;
import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import models.validators.TopValidator;
import services.AttendanceService;

/**
 * 勤怠に関する処理を行うActionクラス
 *
 */
public class AttendanceAction extends ActionBase {

    private AttendanceService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new AttendanceService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する勤怠データを取得
        int page = getPage();
        List<AttendanceView> attendances = service.getAllPerPage(page);

        //全勤怠データの件数を取得
        long attendancesCount = service.countAll();

        putRequestScope(AttributeConst.ATTENDANCES, attendances); //取得した勤怠データ
        putRequestScope(AttributeConst.ATT_COUNT, attendancesCount); //全ての勤怠データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_ATT_INDEX);
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {
          System.out.println("テスト");

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //今日の日付を設定
            LocalDate day = LocalDate.now();


            //出勤打刻で現在時間を設定
            LocalTime timein = LocalTime.now();

            //現在の日付を条件に勤怠データを取得する
            AttendanceView av = service.findDate(day);
            if(av != null) {

                //登録中にエラーがあった場合
                List<String> errors = TopValidator.validate(av);

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ATTENDANCE, av);//入力された勤怠情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //TOP画面を再表示
                forward(ForwardConst.FW_TOP_INDEX);

                return ;

            }


            //セッションからログイン中の従業員情報を取得
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            //パラメータの値をもとに勤怠情報のインスタンスを作成する
            av = new AttendanceView(
                    null,
                    ev, //ログインしている従業員を、勤怠作成者として登録する
                    day,
                    null,
                    timein,
                    null,
                    null,
                    null);

            //勤怠情報登録
            List<String> errors = service.create(av);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ATTENDANCE, av);//入力された勤怠情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //TOP画面を再表示
                forward(ForwardConst.FW_TOP_INDEX);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);
            }
        }
    }

    /**
     * 退勤時間を登録する
     * @throws ServletException
     * @throws IOException
     */
    public void timeOut() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //今日の日付を設定
            LocalDate day = LocalDate.now();

            //現在の日付を条件に勤怠データを取得する
            AttendanceView av = service.findDate(day);

            if(av.getTimeOut() != null) {

                //登録中にエラーがあった場合
                List<String> errors = TopValidator.validate(av);

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ATTENDANCE, av);//入力された勤怠情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //TOP画面を再表示
                forward(ForwardConst.FW_TOP_INDEX);

                return ;

            }

            //退勤打刻で現在時間を設定
            LocalTime timeout = LocalTime.now();

            //現時刻を設定する
            av.setTimeOut(timeout);

            //勤怠データを更新する
            List<String> errors = service.update(av);


            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ATTENDANCE, av); //入力された商談情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //TOP画面を再表示
                forward(ForwardConst.FW_TOP_INDEX);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);

            }
        }
    }

    /**
     * 体温の登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void bodyTemperature() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

          //今日の日付を設定
            LocalDate day = LocalDate.now();

            //現在の日付を条件に勤怠データを取得する
            AttendanceView av = service.findDate(day);

            if(av.getBodyTemperature() != null) {

                //登録中にエラーがあった場合
                List<String> errors = TopValidator.validate(av);

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ATTENDANCE, av);//入力された勤怠情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //TOP画面を再表示
                forward(ForwardConst.FW_TOP_INDEX);

                return ;

            }

            //入力された体温を設定する
            av.setBodyTemperature(getRequestParam(AttributeConst.ATT_BODY));

            //勤怠データを更新する
            List<String> errors = service.update(av);


            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ATTENDANCE, av); //入力された商談情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //TOP画面を再表示
                forward(ForwardConst.FW_TOP_INDEX);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);

            }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に勤怠データを取得する
        AttendanceView av = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

        if (av == null) {
            //該当の勤怠データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.ATTENDANCE, av); //取得した勤怠データ

            //詳細画面を表示
            forward(ForwardConst.FW_ATT_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に勤怠データを取得する
        AttendanceView av = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        if (av == null || ev.getId() != av.getEmployee().getId()) {
            //該当の勤怠データが存在しない、または
            //ログインしている従業員が勤怠の作成者でない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.ATTENDANCE, av); //取得した勤怠データ

            //編集画面を表示
            forward(ForwardConst.FW_ATT_EDIT);
        }

    }

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に勤怠データを取得する
            AttendanceView av = service.findOne(toNumber(getRequestParam(AttributeConst.ATT_ID)));

            //入力された勤怠内容を設定する
            av.setTimeIn(toLocalTime(getRequestParam(AttributeConst.ATT_IN)));
            av.setTimeOut(toLocalTime(getRequestParam(AttributeConst.ATT_OUT)));
            av.setBodyTemperature(getRequestParam(AttributeConst.ATT_BODY));

            //勤怠データを更新する
            List<String> errors = service.update(av);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ATTENDANCE, av); //入力された勤怠情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_ATT_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_ATT, ForwardConst.CMD_INDEX);

            }
        }
    }

}