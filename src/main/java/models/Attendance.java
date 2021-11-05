package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 勤怠データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_ATT)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_ATT_GET_ALL,
            query = JpaConst.Q_ATT_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_ATT_COUNT,
            query = JpaConst.Q_ATT_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_ATT_GET_ALL_MINE,
            query = JpaConst.Q_ATT_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_ATT_COUNT_ALL_MINE,
            query = JpaConst.Q_ATT_COUNT_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_ATT_GET_DATE,
            query = JpaConst.Q_ATT_GET_DATE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Attendance {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.ATT_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 勤怠を登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ATT_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * いつの勤怠かを示す日付
     */
    @Column(name = JpaConst.ATT_COL_ATT_DATE, nullable = true)
    private LocalDate attendanceDate;

    /**
     * 体温記録
     */
    @Column(name = JpaConst.ATT_COL_BODY_TEMPERATURE, nullable = true)
    private String bodyTemperature;

    /**
     * 出勤時間
     */
    @Column(name = JpaConst.ATT_COL_TIME_IN, nullable = false)
    private LocalTime timeIn;

    /**
     * 退勤時間
     */
    @Column(name = JpaConst.ATT_COL_TIME_OUT, nullable = true)
    private LocalTime timeOut;


    /**
     * 登録日時
     */
    @Column(name = JpaConst.ATT_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.ATT_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}
