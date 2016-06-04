package com.wang.dribbble.data.model;

import java.util.List;

/**
 * Created by Jack Wang on 2016/6/2.
 */
public class Shots {


    /**
     * id : 471756
     * title : Sasquatch
     * description : <p>Quick, messy, five minute sketch of something that might become a fictional something.</p>
     * width : 400
     * height : 300
     * images : {"hidpi":null,"normal":"https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch.png","teaser":"https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch_teaser.png"}
     * views_count : 4372
     * likes_count : 149
     * comments_count : 27
     * attachments_count : 0
     * rebounds_count : 2
     * buckets_count : 8
     * created_at : 2012-03-15T01:52:33Z
     * updated_at : 2012-03-15T02:12:57Z
     * html_url : https://dribbble.com/shots/471756-Sasquatch
     * attachments_url : https://api.dribbble.com/v1/shots/471756/attachments
     * buckets_url : https://api.dribbble.com/v1/shots/471756/buckets
     * comments_url : https://api.dribbble.com/v1/shots/471756/comments
     * likes_url : https://api.dribbble.com/v1/shots/471756/likes
     * projects_url : https://api.dribbble.com/v1/shots/471756/projects
     * rebounds_url : https://api.dribbble.com/v1/shots/471756/rebounds
     * animated : false
     * tags : ["fiction","sasquatch","sketch","wip"]
     * user : {"id":1,"name":"Dan Cederholm","username":"simplebits","html_url":"https://dribbble.com/simplebits","avatar_url":"https://d13yacurqjgara.cloudfront.net/users/1/avatars/normal/dc.jpg?1371679243","bio":"Co-founder &amp; designer of <a href=\"https://dribbble.com/dribbble\">@Dribbble<\/a>. Principal of SimpleBits. Aspiring clawhammer banjoist.","location":"Salem, MA","links":{"web":"http://simplebits.com","twitter":"https://twitter.com/simplebits"},"buckets_count":10,"comments_received_count":3395,"followers_count":29262,"followings_count":1728,"likes_count":34954,"likes_received_count":27568,"projects_count":8,"rebounds_received_count":504,"shots_count":214,"teams_count":1,"can_upload_shot":true,"type":"Player","pro":true,"buckets_url":"https://dribbble.com/v1/users/1/buckets","followers_url":"https://dribbble.com/v1/users/1/followers","following_url":"https://dribbble.com/v1/users/1/following","likes_url":"https://dribbble.com/v1/users/1/likes","shots_url":"https://dribbble.com/v1/users/1/shots","teams_url":"https://dribbble.com/v1/users/1/teams","created_at":"2009-07-08T02:51:22Z","updated_at":"2014-02-22T17:10:33Z"}
     * team : {"id":39,"name":"Dribbble","username":"dribbble","html_url":"https://dribbble.com/dribbble","avatar_url":"https://d13yacurqjgara.cloudfront.net/users/39/avatars/normal/apple-flat-precomposed.png?1388527574","bio":"Show and tell for designers. This is Dribbble on Dribbble.","location":"Salem, MA","links":{"web":"http://dribbble.com","twitter":"https://twitter.com/dribbble"},"buckets_count":1,"comments_received_count":2037,"followers_count":25011,"followings_count":6120,"likes_count":44,"likes_received_count":15811,"members_count":7,"projects_count":4,"rebounds_received_count":416,"shots_count":91,"can_upload_shot":true,"type":"Team","pro":false,"buckets_url":"https://dribbble.com/v1/users/39/buckets","followers_url":"https://dribbble.com/v1/users/39/followers","following_url":"https://dribbble.com/v1/users/39/following","likes_url":"https://dribbble.com/v1/users/39/likes","members_url":"https://dribbble.com/v1/teams/39/members","shots_url":"https://dribbble.com/v1/users/39/shots","team_shots_url":"https://dribbble.com/v1/users/39/teams","created_at":"2009-08-18T18:34:31Z","updated_at":"2014-02-14T22:32:11Z"}
     */

    public int id;
    public String title;
    public String description;
    public int width;
    public int height;
    /**
     * hidpi : null
     * normal : https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch.png
     * teaser : https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch_teaser.png
     */

    public ImagesBean images;
    public int views_count;
    public int likes_count;
    public int comments_count;
    public int attachments_count;
    public int rebounds_count;
    public int buckets_count;
    public String created_at;
    public String updated_at;
    public String html_url;
    public String attachments_url;
    public String buckets_url;
    public String comments_url;
    public String likes_url;
    public String projects_url;
    public String rebounds_url;
    public boolean animated;
    /**
     * id : 1
     * name : Dan Cederholm
     * username : simplebits
     * html_url : https://dribbble.com/simplebits
     * avatar_url : https://d13yacurqjgara.cloudfront.net/users/1/avatars/normal/dc.jpg?1371679243
     * bio : Co-founder &amp; designer of <a href="https://dribbble.com/dribbble">@Dribbble</a>. Principal of SimpleBits. Aspiring clawhammer banjoist.
     * location : Salem, MA
     * links : {"web":"http://simplebits.com","twitter":"https://twitter.com/simplebits"}
     * buckets_count : 10
     * comments_received_count : 3395
     * followers_count : 29262
     * followings_count : 1728
     * likes_count : 34954
     * likes_received_count : 27568
     * projects_count : 8
     * rebounds_received_count : 504
     * shots_count : 214
     * teams_count : 1
     * can_upload_shot : true
     * type : Player
     * pro : true
     * buckets_url : https://dribbble.com/v1/users/1/buckets
     * followers_url : https://dribbble.com/v1/users/1/followers
     * following_url : https://dribbble.com/v1/users/1/following
     * likes_url : https://dribbble.com/v1/users/1/likes
     * shots_url : https://dribbble.com/v1/users/1/shots
     * teams_url : https://dribbble.com/v1/users/1/teams
     * created_at : 2009-07-08T02:51:22Z
     * updated_at : 2014-02-22T17:10:33Z
     */

    public UserBean user;
    /**
     * id : 39
     * name : Dribbble
     * username : dribbble
     * html_url : https://dribbble.com/dribbble
     * avatar_url : https://d13yacurqjgara.cloudfront.net/users/39/avatars/normal/apple-flat-precomposed.png?1388527574
     * bio : Show and tell for designers. This is Dribbble on Dribbble.
     * location : Salem, MA
     * links : {"web":"http://dribbble.com","twitter":"https://twitter.com/dribbble"}
     * buckets_count : 1
     * comments_received_count : 2037
     * followers_count : 25011
     * followings_count : 6120
     * likes_count : 44
     * likes_received_count : 15811
     * members_count : 7
     * projects_count : 4
     * rebounds_received_count : 416
     * shots_count : 91
     * can_upload_shot : true
     * type : Team
     * pro : false
     * buckets_url : https://dribbble.com/v1/users/39/buckets
     * followers_url : https://dribbble.com/v1/users/39/followers
     * following_url : https://dribbble.com/v1/users/39/following
     * likes_url : https://dribbble.com/v1/users/39/likes
     * members_url : https://dribbble.com/v1/teams/39/members
     * shots_url : https://dribbble.com/v1/users/39/shots
     * team_shots_url : https://dribbble.com/v1/users/39/teams
     * created_at : 2009-08-18T18:34:31Z
     * updated_at : 2014-02-14T22:32:11Z
     */

    public TeamBean team;
    public List<String> tags;

    public static class ImagesBean {
        public String hidpi;
        public String normal;
        public String teaser;
    }

    public static class UserBean {
        public int id;
        public String name;
        public String username;
        public String html_url;
        public String avatar_url;
        public String bio;
        public String location;
        /**
         * web : http://simplebits.com
         * twitter : https://twitter.com/simplebits
         */

        public LinksBean links;
        public int buckets_count;
        public int comments_received_count;
        public int followers_count;
        public int followings_count;
        public int likes_count;
        public int likes_received_count;
        public int projects_count;
        public int rebounds_received_count;
        public int shots_count;
        public int teams_count;
        public boolean can_upload_shot;
        public String type;
        public boolean pro;
        public String buckets_url;
        public String followers_url;
        public String following_url;
        public String likes_url;
        public String shots_url;
        public String teams_url;
        public String created_at;
        public String updated_at;

        public static class LinksBean {
            public String web;
            public String twitter;
        }
    }

    public static class TeamBean {
        public int id;
        public String name;
        public String username;
        public String html_url;
        public String avatar_url;
        public String bio;
        public String location;
        /**
         * web : http://dribbble.com
         * twitter : https://twitter.com/dribbble
         */

        public LinksBean links;
        public int buckets_count;
        public int comments_received_count;
        public int followers_count;
        public int followings_count;
        public int likes_count;
        public int likes_received_count;
        public int members_count;
        public int projects_count;
        public int rebounds_received_count;
        public int shots_count;
        public boolean can_upload_shot;
        public String type;
        public boolean pro;
        public String buckets_url;
        public String followers_url;
        public String following_url;
        public String likes_url;
        public String members_url;
        public String shots_url;
        public String team_shots_url;
        public String created_at;
        public String updated_at;

        public static class LinksBean {
            public String web;
            public String twitter;
        }
    }
}
