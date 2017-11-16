package com.example.guhao.mytrail;

import java.util.List;

/**
 * Created by DannyPham on 11/15/17.
 */

public class Trail {

    private List<PlacesBean> places;

    public List<PlacesBean> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlacesBean> places) {
        this.places = places;
    }

    public static class PlacesBean {
        /**
         * city : Roanoke
         * state : Virginia
         * country : United States
         * name : Carvin's Cove Trail system
         * parent_id : null
         * unique_id : 3709
         * directions : Two entrances: 1. Reservior Road off US 11 at Hollins. Must obtain access permit to trails at office at this location. 2.Carvin's Cove Road off of HWY 311 near Salem, VA.Go to end of Carvin's Cove raod, new parking lot being constructed for trail users.
         * lat : 37.38463
         * lon : -80.00598
         * description : null
         * date_created : null
         * children : []
         * activities : [{"name":"Carvin's Cove Trail system","unique_id":"2-857","place_id":3709,"activity_type_id":2,"activity_type_name":"hiking","url":"http://www.tripleblaze.com/trail.php?c=3&i=857","attribs":{"\"length\"":"\"30\""},"description":"Carvin's Cove Trail system features 30 miles of hiking trails near Roanoke, VA.","length":30,"activity_type":{"created_at":"2012-08-15T16:12:21Z","id":2,"name":"hiking","updated_at":"2012-08-15T16:12:21Z"},"thumbnail":null,"rank":null,"rating":0}]
         */

        private String city;
        private String state;
        private String country;
        private String name;
        private Object parent_id;
        private int unique_id;
        private String directions;
        private double lat;
        private double lon;
        private Object description;
        private Object date_created;
        private List<?> children;
        private List<ActivitiesBean> activities;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getParent_id() {
            return parent_id;
        }

        public void setParent_id(Object parent_id) {
            this.parent_id = parent_id;
        }

        public int getUnique_id() {
            return unique_id;
        }

        public void setUnique_id(int unique_id) {
            this.unique_id = unique_id;
        }

        public String getDirections() {
            return directions;
        }

        public void setDirections(String directions) {
            this.directions = directions;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getDate_created() {
            return date_created;
        }

        public void setDate_created(Object date_created) {
            this.date_created = date_created;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }

        public List<ActivitiesBean> getActivities() {
            return activities;
        }

        public void setActivities(List<ActivitiesBean> activities) {
            this.activities = activities;
        }

        public static class ActivitiesBean {
            /**
             * name : Carvin's Cove Trail system
             * unique_id : 2-857
             * place_id : 3709
             * activity_type_id : 2
             * activity_type_name : hiking
             * url : http://www.tripleblaze.com/trail.php?c=3&i=857
             * attribs : {"\"length\"":"\"30\""}
             * description : Carvin's Cove Trail system features 30 miles of hiking trails near Roanoke, VA.
             * length : 30.0
             * activity_type : {"created_at":"2012-08-15T16:12:21Z","id":2,"name":"hiking","updated_at":"2012-08-15T16:12:21Z"}
             * thumbnail : null
             * rank : null
             * rating : 0.0
             */

            private String name;
            private String unique_id;
            private int place_id;
            private int activity_type_id;
            private String activity_type_name;
            private String url;
            private AttribsBean attribs;
            private String description;
            private double length;
            private ActivityTypeBean activity_type;
            private Object thumbnail;
            private Object rank;
            private double rating;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUnique_id() {
                return unique_id;
            }

            public void setUnique_id(String unique_id) {
                this.unique_id = unique_id;
            }

            public int getPlace_id() {
                return place_id;
            }

            public void setPlace_id(int place_id) {
                this.place_id = place_id;
            }

            public int getActivity_type_id() {
                return activity_type_id;
            }

            public void setActivity_type_id(int activity_type_id) {
                this.activity_type_id = activity_type_id;
            }

            public String getActivity_type_name() {
                return activity_type_name;
            }

            public void setActivity_type_name(String activity_type_name) {
                this.activity_type_name = activity_type_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public AttribsBean getAttribs() {
                return attribs;
            }

            public void setAttribs(AttribsBean attribs) {
                this.attribs = attribs;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public double getLength() {
                return length;
            }

            public void setLength(double length) {
                this.length = length;
            }

            public ActivityTypeBean getActivity_type() {
                return activity_type;
            }

            public void setActivity_type(ActivityTypeBean activity_type) {
                this.activity_type = activity_type;
            }

            public Object getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(Object thumbnail) {
                this.thumbnail = thumbnail;
            }

            public Object getRank() {
                return rank;
            }

            public void setRank(Object rank) {
                this.rank = rank;
            }

            public double getRating() {
                return rating;
            }

            public void setRating(double rating) {
                this.rating = rating;
            }

            public static class AttribsBean {
//                @com.google.gson.annotations.SerializedName(""length"")
                private String _$Length63; // FIXME check this code

                public String get_$Length63() {
                    return _$Length63;
                }

                public void set_$Length63(String _$Length63) {
                    this._$Length63 = _$Length63;
                }
            }

            public static class ActivityTypeBean {
                /**
                 * created_at : 2012-08-15T16:12:21Z
                 * id : 2
                 * name : hiking
                 * updated_at : 2012-08-15T16:12:21Z
                 */

                private String created_at;
                private int id;
                private String name;
                private String updated_at;

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

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

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }
            }
        }
    }
}
