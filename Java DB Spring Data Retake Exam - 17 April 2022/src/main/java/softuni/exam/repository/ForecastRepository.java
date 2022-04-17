package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DaysOfWeek;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    @Query("SELECT f FROM Forecast f WHERE f.city = :city AND f.daysOfWeek = :day")
    Forecast findForecastByCityWhereDaysOfWeek(@Param(value = "city") City city,
                                               @Param(value = "day") DaysOfWeek daysOfWeek);

    @Query("SELECT f FROM Forecast f WHERE f.daysOfWeek = :day AND f.city.population < :population " +
            "ORDER BY f.maxTemperature DESC, f.id")
    List<Forecast> FindAllByDaysOfWeekAndLessPopulation(@Param(value = "day") DaysOfWeek daysOfWeek,
                                                        @Param(value = "population") int lessPopulation);
}
