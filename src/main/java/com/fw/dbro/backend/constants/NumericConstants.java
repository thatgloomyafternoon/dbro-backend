package com.fw.dbro.backend.constants;

import java.math.BigDecimal;

public class NumericConstants {

  private static final int NUMERIC_0 = 0;
  private static final int NUMERIC_3 = 3;
  private static final int NUMERIC_8 = 8;
  private static final int NUMERIC_10 = 10;
  private static final int NUMERIC_15 = 15;
  private static final int NUMERIC_16 = 16;
  private static final int NUMERIC_30 = 30;
  private static final int NUMERIC_31 = 31;
  private static final int NUMERIC_100 = 100;
  private static final int NUMERIC_200 = 200;
  private static final int NUMERIC_300 = 300;
  private static final int NUMERIC_1000 = 1000;

  /******************************************************************************/

  public static final int SYSCONFIG_TYPE_NAME_COLUMN_MAX_LENGTH = NUMERIC_30;

  public static final int SYSCONFIG_KEY_COLUMN_MAX_LENGTH = NUMERIC_100;

  public static final int SYSCONFIG_VALUE_COLUMN_MAX_LENGTH = NUMERIC_1000;

  public static final int EMAIL_COLUMN_MIN_LENGTH = NUMERIC_3;
  public static final int EMAIL_COLUMN_MAX_LENGTH = NUMERIC_100;

  public static final int PASSWORD_COLUMN_MIN_LENGTH = NUMERIC_8;
  public static final int PASSWORD_COLUMN_MAX_LENGTH = NUMERIC_300;
  public static final int PASSWORD_MIN_LENGTH = NUMERIC_8;
  public static final int PASSWORD_MAX_LENGTH = NUMERIC_30;

  public static final int NAME_COLUMN_MIN_LENGTH = NUMERIC_3;
  public static final int NAME_COLUMN_MAX_LENGTH = NUMERIC_100;

  public static final int JWT_COLUMN_MAX_LENGTH = NUMERIC_1000;

  public static final int NIK_COLUMN_MIN_LENGTH = NUMERIC_10;
  public static final int NIK_COLUMN_MAX_LENGTH = NUMERIC_15;

  public static final int BIRTH_PLACE_COLUMN_MIN_LENGTH = NUMERIC_3;
  public static final int BIRTH_PLACE_COLUMN_MAX_LENGTH = NUMERIC_30;

  public static final int KTP_COLUMN_MIN_LENGTH = NUMERIC_16;
  public static final int KTP_COLUMN_MAX_LENGTH = NUMERIC_16;

  public static final int NPWP_COLUMN_MIN_LENGTH = NUMERIC_16;
  public static final int NPWP_COLUMN_MAX_LENGTH = NUMERIC_16;

  public static final int PHONE_COLUMN_MIN_LENGTH = NUMERIC_10;
  public static final int PHONE_COLUMN_MAX_LENGTH = NUMERIC_15;

  public static final int ADDRESS_COLUMN_MIN_LENGTH = NUMERIC_3;
  public static final int ADDRESS_COLUMN_MAX_LENGTH = NUMERIC_200;

  public static final int BSI_ACCOUNT_COLUMN_MIN_LENGTH = NUMERIC_10;
  public static final int BSI_ACCOUNT_COLUMN_MAX_LENGTH = NUMERIC_16;

  public static final int BPJS_COLUMN_MIN_LENGTH = NUMERIC_16;
  public static final int BPJS_COLUMN_MAX_LENGTH = NUMERIC_16;

  public static final int ATTENDANCE_COLUMN_MIN_LENGTH = NUMERIC_0;
  public static final int ATTENDANCE_COLUMN_MAX_LENGTH = NUMERIC_31;

  public static final BigDecimal BIGD_25 = new BigDecimal("25");
  public static final BigDecimal BIGD_1_PER_173 = new BigDecimal("0.005780347");
  public static final BigDecimal BIGD_60_PERCENT = new BigDecimal("60").divide(new BigDecimal("100"));

}
