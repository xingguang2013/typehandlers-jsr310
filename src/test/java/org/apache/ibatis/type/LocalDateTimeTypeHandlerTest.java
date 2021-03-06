/**
 *    Copyright 2016-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.type;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LocalDateTimeTypeHandlerTest extends BaseTypeHandlerTest {

  private static final TypeHandler<LocalDateTime> TYPE_HANDLER = new LocalDateTimeTypeHandler();
  private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
  private static final Timestamp TIMESTAMP = Timestamp.valueOf(LOCAL_DATE_TIME);

  @Override
  @Test
  public void shouldSetParameter() throws Exception {
    TYPE_HANDLER.setParameter(ps, 1, LOCAL_DATE_TIME, null);
    verify(ps).setTimestamp(1, TIMESTAMP);
  }

  @Override
  @Test
  public void shouldGetResultFromResultSetByName() throws Exception {
    when(rs.getTimestamp("column")).thenReturn(TIMESTAMP);
    assertThat(TYPE_HANDLER.getResult(rs, "column")).isEqualTo(LOCAL_DATE_TIME);
  }

  @Override
  @Test
  public void shouldGetResultNullFromResultSetByName() throws Exception {
    when(rs.getTimestamp("column")).thenReturn(null);
    when(rs.wasNull()).thenReturn(true);
    assertThat(TYPE_HANDLER.getResult(rs, "column")).isNull();
  }

  @Override
  @Test
  public void shouldGetResultFromResultSetByPosition() throws Exception {
    when(rs.getTimestamp(1)).thenReturn(TIMESTAMP);
    assertThat(TYPE_HANDLER.getResult(rs, 1)).isEqualTo(LOCAL_DATE_TIME);
  }

  @Override
  @Test
  public void shouldGetResultNullFromResultSetByPosition() throws Exception {
    when(rs.getTimestamp(1)).thenReturn(null);
    when(rs.wasNull()).thenReturn(true);
    assertThat(TYPE_HANDLER.getResult(rs, 1)).isNull();
  }

  @Override
  @Test
  public void shouldGetResultFromCallableStatement() throws Exception {
    when(cs.getTimestamp(1)).thenReturn(TIMESTAMP);
    assertThat(TYPE_HANDLER.getResult(cs, 1)).isEqualTo(LOCAL_DATE_TIME);
  }

  @Override
  @Test
  public void shouldGetResultNullFromCallableStatement() throws Exception {
    when(cs.getTimestamp(1)).thenReturn(null);
    when(cs.wasNull()).thenReturn(true);
    assertThat(TYPE_HANDLER.getResult(cs, 1)).isNull();
  }
}
