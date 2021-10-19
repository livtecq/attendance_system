package constants;

/**
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
 */
public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "attendance_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //勤怠テーブル
    String TABLE_ATT = "attendances"; //テーブル名
    //勤怠テーブルカラム
    String ATT_COL_ID = "id"; //id
    String ATT_COL_EMP = "employee_id"; //勤怠を作成した従業員のid
    String ATT_COL_ATT_DATE = "attendance_date"; //いつの勤怠かを示す日付
    String ATT_COL_BODY_TEMPERATURE ="body_temperature"; //体温記録
    String ATT_COL_TIME_IN = "time_in"; //出勤時間
    String ATT_COL_TIME_OUT = "time_out"; //退勤時間
    String ATT_COL_COMMENT = "comment"; //コメント
    String ATT_COL_CREATED_AT = "created_at"; //登録日時
    String ATT_COL_UPDATED_AT = "updated_at"; //更新日時

    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_ATT = "attendance"; //日報

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; //従業員

    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll"; //name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_RESISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;
    //全ての勤怠をidの降順に取得する
    String Q_ATT_GET_ALL = ENTITY_ATT + ".getAll";
    String Q_ATT_GET_ALL_DEF = "SELECT a FROM Attendance AS a ORDER BY a.id DESC";
    //全ての勤怠の件数を取得する
    String Q_ATT_COUNT = ENTITY_ATT + ".count";
    String Q_ATT_COUNT_DEF = "SELECT COUNT(a) FROM Attendance AS a";
    //指定した従業員が作成した勤怠を全件idの降順で取得する
    String Q_ATT_GET_ALL_MINE = ENTITY_ATT + ".getAllMine";
    String Q_ATT_GET_ALL_MINE_DEF = "SELECT a FROM Attendance AS a WHERE a.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY a.id DESC";
    //指定した従業員が作成した勤怠の件数を取得する
    String Q_ATT_COUNT_ALL_MINE = ENTITY_ATT + ".countAllMine";
    String Q_ATT_COUNT_ALL_MINE_DEF = "SELECT COUNT(a) FROM Attendance AS a WHERE a.employee = :" + JPQL_PARM_EMPLOYEE;

}