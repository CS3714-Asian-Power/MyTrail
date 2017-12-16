package com.example.guhao.mytrail.data;

import java.util.List;

/**
 * Author: GuHao
 * Date: 12/16/17
 * Time: 3:04 AM
 * Desc:
 */

public class Weather {

    /**
     * city : {"id":4785167,"name":"Shawsville","coord":{"lon":-80.2554,"lat":37.1685},"country":"US","population":1310}
     * cod : 200
     * message : 49.6931329
     * cnt : 5
     * list : [{"dt":1513357200,"temp":{"day":271.54,"min":271.54,"max":271.54,"night":271.54,"eve":271.54,"morn":271.54},"pressure":954.13,"humidity":61,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01n"}],"speed":3.66,"deg":294,"clouds":0},{"dt":1513443600,"temp":{"day":279.84,"min":267.32,"max":280.42,"night":267.32,"eve":274.9,"morn":271.11},"pressure":957.21,"humidity":41,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":3.22,"deg":280,"clouds":0},{"dt":1513530000,"temp":{"day":281.03,"min":267.31,"max":281.03,"night":275.13,"eve":277.96,"morn":267.31},"pressure":961.45,"humidity":45,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":1.42,"deg":169,"clouds":92},{"dt":1513616400,"temp":{"day":288.67,"min":277.39,"max":288.67,"night":279.62,"eve":284.27,"morn":277.39},"pressure":977.92,"humidity":0,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":2.61,"deg":260,"clouds":7},{"dt":1513702800,"temp":{"day":285.57,"min":275.95,"max":285.57,"night":275.95,"eve":282.54,"morn":278.13},"pressure":981.3,"humidity":0,"weather":[{"id":800,"main":"Clear","description":"sky is clear","icon":"01d"}],"speed":4.03,"deg":291,"clouds":14}]
     */

    private CityBean city;
    private String cod;
    private double message;
    private int cnt;
    private List<ListBean> list;

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class CityBean {
        /**
         * id : 4785167
         * name : Shawsville
         * coord : {"lon":-80.2554,"lat":37.1685}
         * country : US
         * population : 1310
         */

        private int id;
        private String name;
        private CoordBean coord;
        private String country;
        private int population;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CoordBean getCoord() {
            return coord;
        }

        public void setCoord(CoordBean coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public static class CoordBean {
            /**
             * lon : -80.2554
             * lat : 37.1685
             */

            private double lon;
            private double lat;

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }
    }

    public static class ListBean {
        /**
         * dt : 1513357200
         * temp : {"day":271.54,"min":271.54,"max":271.54,"night":271.54,"eve":271.54,"morn":271.54}
         * pressure : 954.13
         * humidity : 61
         * weather : [{"id":800,"main":"Clear","description":"sky is clear","icon":"01n"}]
         * speed : 3.66
         * deg : 294
         * clouds : 0
         */

        private int dt;
        private TempBean temp;
        private double pressure;
        private int humidity;
        private double speed;
        private int deg;
        private int clouds;
        private List<WeatherBean> weather;

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public TempBean getTemp() {
            return temp;
        }

        public void setTemp(TempBean temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public int getClouds() {
            return clouds;
        }

        public void setClouds(int clouds) {
            this.clouds = clouds;
        }

        public List<WeatherBean> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBean> weather) {
            this.weather = weather;
        }

        public static class TempBean {
            /**
             * day : 271.54
             * min : 271.54
             * max : 271.54
             * night : 271.54
             * eve : 271.54
             * morn : 271.54
             */

            private double day;
            private double min;
            private double max;
            private double night;
            private double eve;
            private double morn;

            public double getDay() {
                return day;
            }

            public void setDay(double day) {
                this.day = day;
            }

            public double getMin() {
                return min;
            }

            public void setMin(double min) {
                this.min = min;
            }

            public double getMax() {
                return max;
            }

            public void setMax(double max) {
                this.max = max;
            }

            public double getNight() {
                return night;
            }

            public void setNight(double night) {
                this.night = night;
            }

            public double getEve() {
                return eve;
            }

            public void setEve(double eve) {
                this.eve = eve;
            }

            public double getMorn() {
                return morn;
            }

            public void setMorn(double morn) {
                this.morn = morn;
            }
        }

        public static class WeatherBean {
            /**
             * id : 800
             * main : Clear
             * description : sky is clear
             * icon : 01n
             */

            private int id;
            private String main;
            private String description;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
