package api.roleAdminTests;

import api.models.RequestRatingExecutor;
import api.models.hourlyTimesheet.ResponseHourTimesheets;
import api.models.schedule.ScheduleRequestDto;
import api.models.schedule.responseShedule.ResponseSchedule;
import api.preRequests.InfoExecutor;
import api.specification.TestBase;
import config.ConfigCenter;
import dataBase.DataBaseQuery;
import helpers.GenerateTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specification.Specs.requestCleanPath;
import static api.specification.Specs.response200;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ExecutorController extends TestBase {

    private final DataBaseQuery bd = new DataBaseQuery();
    private final GenerateTestData g = new GenerateTestData();
    private final InfoExecutor infoExecutor = new InfoExecutor();
    private final int idExecutor = Integer.parseInt(ConfigCenter.configTestData.idExecutor());
    private static final String endpointDeleteSchedule = "/executor/{id}/schedule/{scheduleId}",
            endpointAddedOrCorrectSchedule = "/executor/{id}/schedule",
            endpointRequestExecutorSchedule = "/executor/{id}/schedule",
            endpointRequestExecutorHourlyTimesheet = "/executor/hourly-timesheet",
            endpointExecutorHistoryOfChanges = "/executor/{id}/history",
            endpointExecutorWorkedHours = "/executor/worked-hours",
            endpointExecutorRating = "/executor/rating",
            endpointExecutorPlannedHours = "/executor/planned-hours";

    @Test
    @DisplayName("Суперадминистратор удаляет график работ исполнителя")
    void deleteSchedule() {
        given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .pathParam("id", idExecutor)
                .pathParam("scheduleId", bd.getScheduleId(idExecutor))
                .when()
                .delete(endpointDeleteSchedule)
                .then()
                .spec(response200);
    }

    @Test
    @DisplayName("Суперадминистратор добавляет график работ исполнителю")
    void createScheduleExecutor() {
        ScheduleRequestDto schedule = new ScheduleRequestDto();
        schedule.setBeginHour(9);
        schedule.setStartDate(g.getTomorrowDateString(1));
        schedule.setStartDateLong(g.getTomorrowTimeMillis(1));
        schedule.setEndHour(10);
        schedule.setEndDate(g.getTomorrowDateString(1));
        schedule.setEndDateLong(g.getTomorrowTimeMillis(1));

        given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .pathParam("id", idExecutor)
                .body(schedule)
                .when()
                .post(endpointAddedOrCorrectSchedule)
                .then()
                .spec(response200);
    }

    @Test
    @DisplayName("Администратор получает расписание исполнителя на неделю")
    void getExecutorScheduleForWeek() {
        String monthYear = g.getMonthYear(g.getTomorrowTimeMillis(1));
        String expectMonthNameWithYear = Character.toUpperCase(monthYear.charAt(0)) + monthYear.substring(1);
        String[] expectDayName = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
        ResponseSchedule r = given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .pathParam("id", idExecutor)
                .queryParam("dayInWeek", g.getTomorrowTimeMillis(1))
                .when()
                .get(endpointRequestExecutorSchedule)
                .then()
                .spec(response200)
                .extract().as(ResponseSchedule.class);
        //    Assertions.assertEquals(expectMonthNameWithYear, r.getMonthNameWithYear());
        Assertions.assertEquals(r.getDaysArray().size(), 7);
        for (int i = 0; i < r.getDaysArray().size(); i++) {
            Assertions.assertEquals(r.getDaysArray().get(i).getDayName(), expectDayName[i]);
        }
    }

    @Test
    @DisplayName("Суперадминистратор запрашивает получение почасового табеля")
    void executorHourlyTimesheet() {
        ResponseHourTimesheets r = given()
                .spec(requestCleanPath)
                .queryParam("date", g.getTomorrowDateString(1))
                .header("Authorization", tokenSuperAdmin)
                .when()
                .get(endpointRequestExecutorHourlyTimesheet)
                .then()
                .spec(response200)
                .extract().as(ResponseHourTimesheets.class);
        Assertions.assertEquals(g.getTomorrowDateStringFormat(1), r.getDate());
        for (int i = 0; i < r.getHourTimesheets().size(); i++) {
            Assertions.assertEquals(r.getHourTimesheets().get(i).getStartHour(), i);
        }
    }

    @Test
    @DisplayName("Суперадминистратор просматривает историю изменений исполнителя")
    void executorHistory() {
        given()
                .spec(requestCleanPath)
                .pathParam("id", ConfigCenter.configTestData.idExecutor())
                .header("Authorization", tokenSuperAdmin)
                .when()
                .get(endpointExecutorHistoryOfChanges)
                .then()
                .spec(response200)
                .body("content", notNullValue());
    }

    @Test
    @DisplayName("Суперадминистратор запрашивает общее кол-во проработанных часов исполнителя за период")
    void executorWorkedHour() {
        given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .queryParam("startDate", g.getTomorrowDateString(-3))
                .queryParam("endDate", g.getTomorrowDateString(3))
                .queryParam("id", ConfigCenter.configTestData.idExecutor())
                .when()
                .get(endpointExecutorWorkedHours)
                .then()
                .spec(response200);
    }

    @Test
    @DisplayName("Суперадминистратор вносит изменения рейтинга исполнителя")
    void changeExecutorRating() {
        int expectRattingValue = infoExecutor.getExecutorActualRating(idExecutor) + 1;

        RequestRatingExecutor body = new RequestRatingExecutor();
        body.setId(idExecutor);
        body.setComment("Тест");
        body.setPoints(1);
        given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .body(body)
                .when()
                .post(endpointExecutorRating)
                .then()
                .spec(response200)
                .body("id", equalTo(idExecutor))
                .body("executorRating", equalTo(expectRattingValue));

    }

    @Test
    @DisplayName("Суперадминистратор запрашивает общее кол-во запланированных часов исполнителя за период")
    void executorPlannedHour() {
        given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .queryParam("startDate", g.getTomorrowDateString(0))
                .queryParam("endDate", g.getTomorrowDateString(7))
                .queryParam("id", ConfigCenter.configTestData.idExecutor())
                .when()
                .get(endpointExecutorPlannedHours)
                .then()
                .spec(response200);
    }
}
