package dataBase;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseQuery {
    private DataSource ds = DataSourceProvider.INSTANCE.getDataSource();
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);

    private int randomListSizeCity() {
        String sqlFindAllCity = "SELECT * FROM city where removed = false";
        List<String> listSize = namedParameterJdbcTemplate.query(sqlFindAllCity, (rs, i) -> rs.getString("name"));
        return listSize.size();
    }

    public String getRandomCity() {
        String sqlFindCity = "SELECT name FROM city where removed = false";
        List<String> city = namedParameterJdbcTemplate.query(sqlFindCity, (rs, i) -> rs.getString("name"));
        int max = randomListSizeCity();
        double rnd = Math.random() * max;
        int a = (int) rnd;
        return city.get(a);
    }

    public Float getPriceForHour(String city) {
        String sqlFindPrice = "SELECT price_for_hour FROM city where removed = false and name = :cityName";
        Map<String, Object> map = new HashMap();
        map.put("cityName", city);
        List<String> price = namedParameterJdbcTemplate.query(sqlFindPrice, map, (rs, i) -> rs.getString("price_for_hour"));
        String a = price.get(0);
        //.split("\\.")[0];
        return Float.parseFloat(a);
    }

    public Integer getCityId(String city) {
        String sqlFindPrice = "SELECT id FROM city where removed = false and name = :cityName";
        Map<String, Object> map = new HashMap();
        map.put("cityName", city);
        List<String> price = namedParameterJdbcTemplate.query(sqlFindPrice, map, (rs, i) -> rs.getString("id"));
        return Integer.parseInt(price.get(0));
    }

    public Integer getScheduleId(Integer executorUserId) {
        String sqlFindLastScheduleId = "SELECT id FROM executor_schedule where user_id = :executorId Order by id desc LIMIT 1;";
        Map<String, Object> map = new HashMap<>();
        map.put("executorId", executorUserId);
        List<Integer> scheduleId = namedParameterJdbcTemplate.query(sqlFindLastScheduleId, map, (rs, i) -> rs.getInt("id"));
        return scheduleId.get(0);
    }

    public void deleteCity(Integer cityId) {
        String sqlFindCity = "DELETE FROM city where id = :cityId";
        Map<String, Integer> map = new HashMap<>();
        map.put("cityId", cityId);
        namedParameterJdbcTemplate.update(sqlFindCity, map);
    }

    public int howManyCountRegions(){
        String sqlFindCountRegions = "SELECT id FROM region where removed = false";
        return namedParameterJdbcTemplate.query(sqlFindCountRegions,(rs, i) -> rs.getInt("id")).size();
    }

    public String clientAddress(int clientId, int index){
        String sqlSelectAddress = "SELECT  address FROM address where user_id = :clientId";
        Map<String,Integer> map = new HashMap<>();
        map.put("clientId", clientId);
        List<String> s = namedParameterJdbcTemplate.query(sqlSelectAddress,map,(rs,i)->rs.getString("address"));
        return s.get(index);
    }

}
