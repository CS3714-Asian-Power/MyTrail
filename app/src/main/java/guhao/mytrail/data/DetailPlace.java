package guhao.mytrail.data;

import java.util.List;

/**
 * Created by Daniel Pham on 12/5/17.
 */

public class DetailPlace {


    /**
     * html_attributions : []
     * result : {"address_components":[{"long_name":"1087","short_name":"1087","types":["street_number"]},{"long_name":"Jennelle Road","short_name":"Jennelle Rd","types":["route"]},{"long_name":"Blacksburg","short_name":"Blacksburg","types":["locality","political"]},{"long_name":"B-01","short_name":"B-01","types":["administrative_area_level_3","political"]},{"long_name":"Montgomery County","short_name":"Montgomery County","types":["administrative_area_level_2","political"]},{"long_name":"Virginia","short_name":"VA","types":["administrative_area_level_1","political"]},{"long_name":"United States","short_name":"US","types":["country","political"]},{"long_name":"24060","short_name":"24060","types":["postal_code"]}],"adr_address":"<span class=\"street-address\">1087 Jennelle Rd<\/span>, <span class=\"locality\">Blacksburg<\/span>, <span class=\"region\">VA<\/span> <span class=\"postal-code\">24060<\/span>, <span class=\"country-name\">USA<\/span>","formatted_address":"1087 Jennelle Rd, Blacksburg, VA 24060, USA","geometry":{"location":{"lat":37.18482189999999,"lng":-80.3763869},"viewport":{"northeast":{"lat":37.18617088029148,"lng":-80.3750379197085},"southwest":{"lat":37.18347291970849,"lng":-80.37773588029151}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/generic_recreational-71.png","id":"4d2731aa89e6fa2005c8a73edbd3e52819ef3d36","name":"Ellett Valley Recreational Area","photos":[{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAAQmH3yo6tMVT-dk671ycSd8gqomaD_sbGPR-K90U5ziHYu66fZQo6yKK1-iy5S0oWVxLHaZChLWAi_x3VcS_nMeTkA8aVAa_19-Jl5oSpvupKs8lqxLwutR8jbyPnWc4aEhDMsg5abjCO49FtuXT95b4lGhRLHH3sBZ2LOC-CYzpz6DLZ-qxYYQ","width":5312},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAAz_f-Hf_Ck5JciZ1uk3Bl6qkI_oYXyviBnPBlVUfW95uG76UruRSqjjaVX4wWl_Cq96F0B5WmSOvsID5SeI1pfyNZsFMWnd7rsz3ORN7lE1GOpDe0CvmJNRH8C4e39HxtEhBihd_i80fZE4MAifvLk8aQGhS9U2Fa_w-IyOz2ooHZ9qe8lK3Dfg","width":5312},{"height":5312,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/113794494716482216628/photos\">Brittany Early<\/a>"],"photo_reference":"CmRaAAAA69wWUi8myBSuYzpwMZzNHz47Mt5Qn9W6C7yuiZn1RrfaeXezxh2bBYsMhwjtNuqnsaSE9QB8iUF79URrU5gmqmGOLqPjeaOXzfIwfFCyb0SFinJfcEHG7m0iuZsGgoUsEhC3BNCivw6AYRdnV6iYVdeLGhTVKKJ1LJ3rciV8qRrC3LfjKRWU1g","width":2988},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAA1eBn1uIg0qxMY4_5H8u7kdBoD8yTGxXD4dxGvq0YfXl605niRgy6xLVIY_-i9kQAGRGGuINwSv6F1dCM6B1wB_En1O4nyuWcwELtSQhnSBE9a2E9qL7z2I5pGNo2J3RZEhBkPBKGO4LB1SFX2PR_TqEtGhTpId4xPqqh8eYANmr0Q2k9aohk_A","width":5312},{"height":4048,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/116265101538213708074/photos\">Austin H<\/a>"],"photo_reference":"CmRaAAAASOLAp6KKTxkS7zYepy-wjY4_xBzxCCFKf4LXvWqT8MHKOvyUWXBBu1IBF3lhCvp9HJ4YXjyqJL7gmydrO01Taw17jf3voXl8O9EhV9Kt9OqxW8XST77RxxPqonuY1veLEhB60MOX2_sO2-k76Cn8qo-OGhRI7ixLZlUcYfJy6fGoiJ_LSu11Jg","width":3036},{"height":3264,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/109358691655223547700/photos\">Cody Cole<\/a>"],"photo_reference":"CmRaAAAAOmtDUMG9aDkl-YyzrQj0eKBhv0UmAVdKx2hEE5suAsX1jhuwpcmm_SlZhKpHsDibIkTIU8bPTbwzy6WL69usYT9MmodPOid4lHhKo9tsWpgt4Vf0MOpwTC-p8uphhYqFEhC5Ftxq4mhEoWKqGaCOYOObGhQFRW5xc977ZH6x721mzDZYwM-ywQ","width":1836},{"height":1836,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/109358691655223547700/photos\">Cody Cole<\/a>"],"photo_reference":"CmRaAAAAag0haFz3mvVXQiK5pRQCyyvZXcLCaPdQtWsVLihx8p_5PivWzFPeyCJe6to4MEsz8cmc7HrDn5FvybWSJyCxBIC-BAu89tmbqy-7XypPap0DAdMsLGDrFY7j4kS1mpXUEhBZed8AbWjFk_UdthayXpnoGhQjygpw-0OgLtzKxK1tTaaTBV2GFg","width":3264},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAAFDu9_SjVr8lUAM1ilxVPa0Izndvx28V3ua9X4BkZ_G3ldeogy8T5zFDx8yohCKLLWhq07HNRVo7TYNGI86tj6T9FI62iQpe-AHZbJthRJlbfyX36hU-pHxa4PvI6p09GEhAAQ24FN68F7q4ry_Qm1w5TGhQu6lO2DzWNIQ3pF9GxDiNUqifNmA","width":5312},{"height":3264,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/109358691655223547700/photos\">Cody Cole<\/a>"],"photo_reference":"CmRaAAAA4dfGr9bDtPHcr0KBQWTo1wFVuN_iE0J16EJUCvf1fLkHrYL2nTEO8KoSGRxuSeJyHqw3W8knbdEr4YuJVLKIE81UpYgieISI_bM_jr--4Oimd88kro2YzNl822GXbW0fEhD4QC95PZ7pUl26iQgvpX0aGhT44MMazHBUPc5nnmlzBFbDoDOz0w","width":1836},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAAnUWsCxDdoBwgnqpK1UnXKfuSWhbqXzQ0LbNXPSdSgiYmDRlUE927HJM22fGke-yKVay2wRygQMmrNtRbXDBODq2P3-tHVzstc13CZkAOeMJi-NmOauzPckmtdPB6_RxrEhANzKFMkWSHU19oJJwh91XHGhQMluAWfpWIs5uYvFZGOvdvFNiqhA","width":5312}],"place_id":"ChIJg8RyzyKUTYgRJkjVL0ondyg","rating":3.8,"reference":"CmRRAAAAo70YUQmZEpSDiIEiZYt19J_8pPr1ROoQajpIBAL66lzGxm-cUPyhAnsUPwYniECbGz0KQ4o1mglJO5IeMKSVUvgvYk5qfSz-FspF0-yn0qzYaHexESMZzBNUVqQmHSfoEhA11GaPFxdU2eTWPUfITvpiGhR6DFJUXLTxsnulTqc7Jis9871Npg","reviews":[{"author_name":"Austin H","author_url":"https://www.google.com/maps/contrib/116265101538213708074/reviews","language":"en","profile_photo_url":"https://lh3.googleusercontent.com/-txzd-Z9lyTM/AAAAAAAAAAI/AAAAAAAADno/97FeAuBD9ZM/s128-c0x00000000-cc-rp-mo-ba3/photo.jpg","rating":1,"relative_time_description":"5 months ago","text":"The entrance to the area is easy to miss and has about a 1 ft deep pot hole to one side. It's also on a steep incline and difficult to see on coming traffic when trying to exit. Parking is limited to about 4 cars. The trail is currently overgrown and only usable  if you want to bushwhack. ","time":1498487958},{"author_name":"Sarah Vogl","author_url":"https://www.google.com/maps/contrib/118211228634774353964/reviews","language":"en","profile_photo_url":"https://lh5.googleusercontent.com/-wSh6KdHDlvc/AAAAAAAAAAI/AAAAAAAASY4/Lq7hWscGpRU/s128-c0x00000000-cc-rp-mo-ba2/photo.jpg","rating":5,"relative_time_description":"5 months ago","text":"","time":1499016223},{"author_name":"Jessica Bennett","author_url":"https://www.google.com/maps/contrib/114665764494262421899/reviews","language":"en","profile_photo_url":"https://lh4.googleusercontent.com/-XdBR-sFs814/AAAAAAAAAAI/AAAAAAAAAAA/faogv22f5XI/s128-c0x00000000-cc-rp-mo-ba5/photo.jpg","rating":4,"relative_time_description":"7 months ago","text":"","time":1492113481},{"author_name":"Cody Cole","author_url":"https://www.google.com/maps/contrib/109358691655223547700/reviews","language":"en","profile_photo_url":"https://lh5.googleusercontent.com/-BXzSR1CNRDc/AAAAAAAAAAI/AAAAAAAAAAA/AFiYof13JDebu_6a544qkiS5kM9eaW-INg/s128-c0x00000000-cc-rp-mo/photo.jpg","rating":5,"relative_time_description":"9 months ago","text":"","time":1487530132}],"scope":"GOOGLE","types":["park","point_of_interest","establishment"],"url":"https://maps.google.com/?cid=2915842483329255462","utc_offset":-300,"vicinity":"1087 Jennelle Road, Blacksburg"}
     * status : OK
     */

    private ResultBean result;
    private String status;
    private List<?> html_attributions;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<?> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public static class ResultBean {
        /**
         * address_components : [{"long_name":"1087","short_name":"1087","types":["street_number"]},{"long_name":"Jennelle Road","short_name":"Jennelle Rd","types":["route"]},{"long_name":"Blacksburg","short_name":"Blacksburg","types":["locality","political"]},{"long_name":"B-01","short_name":"B-01","types":["administrative_area_level_3","political"]},{"long_name":"Montgomery County","short_name":"Montgomery County","types":["administrative_area_level_2","political"]},{"long_name":"Virginia","short_name":"VA","types":["administrative_area_level_1","political"]},{"long_name":"United States","short_name":"US","types":["country","political"]},{"long_name":"24060","short_name":"24060","types":["postal_code"]}]
         * adr_address : <span class="street-address">1087 Jennelle Rd</span>, <span class="locality">Blacksburg</span>, <span class="region">VA</span> <span class="postal-code">24060</span>, <span class="country-name">USA</span>
         * formatted_address : 1087 Jennelle Rd, Blacksburg, VA 24060, USA
         * geometry : {"location":{"lat":37.18482189999999,"lng":-80.3763869},"viewport":{"northeast":{"lat":37.18617088029148,"lng":-80.3750379197085},"southwest":{"lat":37.18347291970849,"lng":-80.37773588029151}}}
         * icon : https://maps.gstatic.com/mapfiles/place_api/icons/generic_recreational-71.png
         * id : 4d2731aa89e6fa2005c8a73edbd3e52819ef3d36
         * name : Ellett Valley Recreational Area
         * photos : [{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAAQmH3yo6tMVT-dk671ycSd8gqomaD_sbGPR-K90U5ziHYu66fZQo6yKK1-iy5S0oWVxLHaZChLWAi_x3VcS_nMeTkA8aVAa_19-Jl5oSpvupKs8lqxLwutR8jbyPnWc4aEhDMsg5abjCO49FtuXT95b4lGhRLHH3sBZ2LOC-CYzpz6DLZ-qxYYQ","width":5312},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAAz_f-Hf_Ck5JciZ1uk3Bl6qkI_oYXyviBnPBlVUfW95uG76UruRSqjjaVX4wWl_Cq96F0B5WmSOvsID5SeI1pfyNZsFMWnd7rsz3ORN7lE1GOpDe0CvmJNRH8C4e39HxtEhBihd_i80fZE4MAifvLk8aQGhS9U2Fa_w-IyOz2ooHZ9qe8lK3Dfg","width":5312},{"height":5312,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/113794494716482216628/photos\">Brittany Early<\/a>"],"photo_reference":"CmRaAAAA69wWUi8myBSuYzpwMZzNHz47Mt5Qn9W6C7yuiZn1RrfaeXezxh2bBYsMhwjtNuqnsaSE9QB8iUF79URrU5gmqmGOLqPjeaOXzfIwfFCyb0SFinJfcEHG7m0iuZsGgoUsEhC3BNCivw6AYRdnV6iYVdeLGhTVKKJ1LJ3rciV8qRrC3LfjKRWU1g","width":2988},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAA1eBn1uIg0qxMY4_5H8u7kdBoD8yTGxXD4dxGvq0YfXl605niRgy6xLVIY_-i9kQAGRGGuINwSv6F1dCM6B1wB_En1O4nyuWcwELtSQhnSBE9a2E9qL7z2I5pGNo2J3RZEhBkPBKGO4LB1SFX2PR_TqEtGhTpId4xPqqh8eYANmr0Q2k9aohk_A","width":5312},{"height":4048,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/116265101538213708074/photos\">Austin H<\/a>"],"photo_reference":"CmRaAAAASOLAp6KKTxkS7zYepy-wjY4_xBzxCCFKf4LXvWqT8MHKOvyUWXBBu1IBF3lhCvp9HJ4YXjyqJL7gmydrO01Taw17jf3voXl8O9EhV9Kt9OqxW8XST77RxxPqonuY1veLEhB60MOX2_sO2-k76Cn8qo-OGhRI7ixLZlUcYfJy6fGoiJ_LSu11Jg","width":3036},{"height":3264,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/109358691655223547700/photos\">Cody Cole<\/a>"],"photo_reference":"CmRaAAAAOmtDUMG9aDkl-YyzrQj0eKBhv0UmAVdKx2hEE5suAsX1jhuwpcmm_SlZhKpHsDibIkTIU8bPTbwzy6WL69usYT9MmodPOid4lHhKo9tsWpgt4Vf0MOpwTC-p8uphhYqFEhC5Ftxq4mhEoWKqGaCOYOObGhQFRW5xc977ZH6x721mzDZYwM-ywQ","width":1836},{"height":1836,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/109358691655223547700/photos\">Cody Cole<\/a>"],"photo_reference":"CmRaAAAAag0haFz3mvVXQiK5pRQCyyvZXcLCaPdQtWsVLihx8p_5PivWzFPeyCJe6to4MEsz8cmc7HrDn5FvybWSJyCxBIC-BAu89tmbqy-7XypPap0DAdMsLGDrFY7j4kS1mpXUEhBZed8AbWjFk_UdthayXpnoGhQjygpw-0OgLtzKxK1tTaaTBV2GFg","width":3264},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAAFDu9_SjVr8lUAM1ilxVPa0Izndvx28V3ua9X4BkZ_G3ldeogy8T5zFDx8yohCKLLWhq07HNRVo7TYNGI86tj6T9FI62iQpe-AHZbJthRJlbfyX36hU-pHxa4PvI6p09GEhAAQ24FN68F7q4ry_Qm1w5TGhQu6lO2DzWNIQ3pF9GxDiNUqifNmA","width":5312},{"height":3264,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/109358691655223547700/photos\">Cody Cole<\/a>"],"photo_reference":"CmRaAAAA4dfGr9bDtPHcr0KBQWTo1wFVuN_iE0J16EJUCvf1fLkHrYL2nTEO8KoSGRxuSeJyHqw3W8knbdEr4YuJVLKIE81UpYgieISI_bM_jr--4Oimd88kro2YzNl822GXbW0fEhD4QC95PZ7pUl26iQgvpX0aGhT44MMazHBUPc5nnmlzBFbDoDOz0w","width":1836},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"],"photo_reference":"CmRaAAAAnUWsCxDdoBwgnqpK1UnXKfuSWhbqXzQ0LbNXPSdSgiYmDRlUE927HJM22fGke-yKVay2wRygQMmrNtRbXDBODq2P3-tHVzstc13CZkAOeMJi-NmOauzPckmtdPB6_RxrEhANzKFMkWSHU19oJJwh91XHGhQMluAWfpWIs5uYvFZGOvdvFNiqhA","width":5312}]
         * place_id : ChIJg8RyzyKUTYgRJkjVL0ondyg
         * rating : 3.8
         * reference : CmRRAAAAo70YUQmZEpSDiIEiZYt19J_8pPr1ROoQajpIBAL66lzGxm-cUPyhAnsUPwYniECbGz0KQ4o1mglJO5IeMKSVUvgvYk5qfSz-FspF0-yn0qzYaHexESMZzBNUVqQmHSfoEhA11GaPFxdU2eTWPUfITvpiGhR6DFJUXLTxsnulTqc7Jis9871Npg
         * reviews : [{"author_name":"Austin H","author_url":"https://www.google.com/maps/contrib/116265101538213708074/reviews","language":"en","profile_photo_url":"https://lh3.googleusercontent.com/-txzd-Z9lyTM/AAAAAAAAAAI/AAAAAAAADno/97FeAuBD9ZM/s128-c0x00000000-cc-rp-mo-ba3/photo.jpg","rating":1,"relative_time_description":"5 months ago","text":"The entrance to the area is easy to miss and has about a 1 ft deep pot hole to one side. It's also on a steep incline and difficult to see on coming traffic when trying to exit. Parking is limited to about 4 cars. The trail is currently overgrown and only usable  if you want to bushwhack. ","time":1498487958},{"author_name":"Sarah Vogl","author_url":"https://www.google.com/maps/contrib/118211228634774353964/reviews","language":"en","profile_photo_url":"https://lh5.googleusercontent.com/-wSh6KdHDlvc/AAAAAAAAAAI/AAAAAAAASY4/Lq7hWscGpRU/s128-c0x00000000-cc-rp-mo-ba2/photo.jpg","rating":5,"relative_time_description":"5 months ago","text":"","time":1499016223},{"author_name":"Jessica Bennett","author_url":"https://www.google.com/maps/contrib/114665764494262421899/reviews","language":"en","profile_photo_url":"https://lh4.googleusercontent.com/-XdBR-sFs814/AAAAAAAAAAI/AAAAAAAAAAA/faogv22f5XI/s128-c0x00000000-cc-rp-mo-ba5/photo.jpg","rating":4,"relative_time_description":"7 months ago","text":"","time":1492113481},{"author_name":"Cody Cole","author_url":"https://www.google.com/maps/contrib/109358691655223547700/reviews","language":"en","profile_photo_url":"https://lh5.googleusercontent.com/-BXzSR1CNRDc/AAAAAAAAAAI/AAAAAAAAAAA/AFiYof13JDebu_6a544qkiS5kM9eaW-INg/s128-c0x00000000-cc-rp-mo/photo.jpg","rating":5,"relative_time_description":"9 months ago","text":"","time":1487530132}]
         * scope : GOOGLE
         * types : ["park","point_of_interest","establishment"]
         * url : https://maps.google.com/?cid=2915842483329255462
         * utc_offset : -300
         * vicinity : 1087 Jennelle Road, Blacksburg
         */

        private String adr_address;
        private String formatted_address;
        private GeometryBean geometry;
        private String icon;
        private String id;
        private String name;
        private String place_id;
        private double rating;
        private String reference;
        private String scope;
        private String url;
        private int utc_offset;
        private String vicinity;
        private List<AddressComponentsBean> address_components;
        private List<PhotosBean> photos;
        private List<ReviewsBean> reviews;
        private List<String> types;

        public String getAdr_address() {
            return adr_address;
        }

        public void setAdr_address(String adr_address) {
            this.adr_address = adr_address;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getUtc_offset() {
            return utc_offset;
        }

        public void setUtc_offset(int utc_offset) {
            this.utc_offset = utc_offset;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        public List<AddressComponentsBean> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * location : {"lat":37.18482189999999,"lng":-80.3763869}
             * viewport : {"northeast":{"lat":37.18617088029148,"lng":-80.3750379197085},"southwest":{"lat":37.18347291970849,"lng":-80.37773588029151}}
             */

            private LocationBean location;
            private ViewportBean viewport;

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class LocationBean {
                /**
                 * lat : 37.18482189999999
                 * lng : -80.3763869
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * northeast : {"lat":37.18617088029148,"lng":-80.3750379197085}
                 * southwest : {"lat":37.18347291970849,"lng":-80.37773588029151}
                 */

                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    /**
                     * lat : 37.18617088029148
                     * lng : -80.3750379197085
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    /**
                     * lat : 37.18347291970849
                     * lng : -80.37773588029151
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponentsBean {
            /**
             * long_name : 1087
             * short_name : 1087
             * types : ["street_number"]
             */

            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }

        public static class PhotosBean {
            /**
             * height : 2988
             * html_attributions : ["<a href=\"https://maps.google.com/maps/contrib/114665764494262421899/photos\">Jessica Bennett<\/a>"]
             * photo_reference : CmRaAAAAQmH3yo6tMVT-dk671ycSd8gqomaD_sbGPR-K90U5ziHYu66fZQo6yKK1-iy5S0oWVxLHaZChLWAi_x3VcS_nMeTkA8aVAa_19-Jl5oSpvupKs8lqxLwutR8jbyPnWc4aEhDMsg5abjCO49FtuXT95b4lGhRLHH3sBZ2LOC-CYzpz6DLZ-qxYYQ
             * width : 5312
             */

            private int height;
            private String photo_reference;
            private int width;
            private List<String> html_attributions;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPhoto_reference() {
                return photo_reference;
            }

            public void setPhoto_reference(String photo_reference) {
                this.photo_reference = photo_reference;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<String> getHtml_attributions() {
                return html_attributions;
            }

            public void setHtml_attributions(List<String> html_attributions) {
                this.html_attributions = html_attributions;
            }
        }

        public static class ReviewsBean {
            /**
             * author_name : Austin H
             * author_url : https://www.google.com/maps/contrib/116265101538213708074/reviews
             * language : en
             * profile_photo_url : https://lh3.googleusercontent.com/-txzd-Z9lyTM/AAAAAAAAAAI/AAAAAAAADno/97FeAuBD9ZM/s128-c0x00000000-cc-rp-mo-ba3/photo.jpg
             * rating : 1
             * relative_time_description : 5 months ago
             * text : The entrance to the area is easy to miss and has about a 1 ft deep pot hole to one side. It's also on a steep incline and difficult to see on coming traffic when trying to exit. Parking is limited to about 4 cars. The trail is currently overgrown and only usable  if you want to bushwhack.
             * time : 1498487958
             */

            private String author_name;
            private String author_url;
            private String language;
            private String profile_photo_url;
            private int rating;
            private String relative_time_description;
            private String text;
            private int time;

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getAuthor_url() {
                return author_url;
            }

            public void setAuthor_url(String author_url) {
                this.author_url = author_url;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getProfile_photo_url() {
                return profile_photo_url;
            }

            public void setProfile_photo_url(String profile_photo_url) {
                this.profile_photo_url = profile_photo_url;
            }

            public int getRating() {
                return rating;
            }

            public void setRating(int rating) {
                this.rating = rating;
            }

            public String getRelative_time_description() {
                return relative_time_description;
            }

            public void setRelative_time_description(String relative_time_description) {
                this.relative_time_description = relative_time_description;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }
        }
    }
}
