package ml.wonwoo.githubissuesdashboard.github

import ml.wonwoo.githubissuesdashboard.GithubProperties
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.StepVerifier

internal class GithubClientTests {

    private val mockWebServer = MockWebServer()

    private val githubClient: GithubClient = GithubClient(WebClient.builder().baseUrl(mockWebServer.url("/").toString())
        , GithubProperties(null, mockWebServer.url("/").toString()))


    @Test
    fun `github fetch events test`() {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .setBody("""[
    {
        "id": 2699261751,
        "node_id": "MDE1OlN1YnNjcmliZWRFdmVudDI2OTkyNjE3NTE=",
        "url": "https://api.github.com/repos/spring-projects/spring-boot/issues/events/2699261751",
        "actor": {
            "login": "mbhave",
            "id": 1761408,
            "node_id": "MDQ6VXNlcjE3NjE0MDg=",
            "avatar_url": "https://avatars0.githubusercontent.com/u/1761408?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/mbhave",
            "html_url": "https://github.com/mbhave",
            "followers_url": "https://api.github.com/users/mbhave/followers",
            "following_url": "https://api.github.com/users/mbhave/following{/other_user}",
            "gists_url": "https://api.github.com/users/mbhave/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/mbhave/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/mbhave/subscriptions",
            "organizations_url": "https://api.github.com/users/mbhave/orgs",
            "repos_url": "https://api.github.com/users/mbhave/repos",
            "events_url": "https://api.github.com/users/mbhave/events{/privacy}",
            "received_events_url": "https://api.github.com/users/mbhave/received_events",
            "type": "User",
            "site_admin": false
        },
        "event": "subscribed",
        "commit_id": null,
        "commit_url": null,
        "created_at": "2019-10-09T14:30:44Z",
        "issue": {
            "url": "https://api.github.com/repos/spring-projects/spring-boot/issues/13822",
            "repository_url": "https://api.github.com/repos/spring-projects/spring-boot",
            "labels_url": "https://api.github.com/repos/spring-projects/spring-boot/issues/13822/labels{/name}",
            "comments_url": "https://api.github.com/repos/spring-projects/spring-boot/issues/13822/comments",
            "events_url": "https://api.github.com/repos/spring-projects/spring-boot/issues/13822/events",
            "html_url": "https://github.com/spring-projects/spring-boot/issues/13822",
            "id": 342616593,
            "node_id": "MDU6SXNzdWUzNDI2MTY1OTM=",
            "number": 13822,
            "title": "@AutoConfigureMockMvc imports auto-configurations manually",
            "user": {
                "login": "snicoll",
                "id": 490484,
                "node_id": "MDQ6VXNlcjQ5MDQ4NA==",
                "avatar_url": "https://avatars0.githubusercontent.com/u/490484?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/snicoll",
                "html_url": "https://github.com/snicoll",
                "followers_url": "https://api.github.com/users/snicoll/followers",
                "following_url": "https://api.github.com/users/snicoll/following{/other_user}",
                "gists_url": "https://api.github.com/users/snicoll/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/snicoll/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/snicoll/subscriptions",
                "organizations_url": "https://api.github.com/users/snicoll/orgs",
                "repos_url": "https://api.github.com/users/snicoll/repos",
                "events_url": "https://api.github.com/users/snicoll/events{/privacy}",
                "received_events_url": "https://api.github.com/users/snicoll/received_events",
                "type": "User",
                "site_admin": false
            },
            "labels": [
                {
                    "id": 16916891,
                    "node_id": "MDU6TGFiZWwxNjkxNjg5MQ==",
                    "url": "https://api.github.com/repos/spring-projects/spring-boot/labels/type:%20bug",
                    "name": "type: bug",
                    "color": "e3d9fc",
                    "default": false
                }
            ],
            "state": "closed",
            "locked": false,
            "assignee": {
                "login": "mbhave",
                "id": 1761408,
                "node_id": "MDQ6VXNlcjE3NjE0MDg=",
                "avatar_url": "https://avatars0.githubusercontent.com/u/1761408?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/mbhave",
                "html_url": "https://github.com/mbhave",
                "followers_url": "https://api.github.com/users/mbhave/followers",
                "following_url": "https://api.github.com/users/mbhave/following{/other_user}",
                "gists_url": "https://api.github.com/users/mbhave/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/mbhave/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/mbhave/subscriptions",
                "organizations_url": "https://api.github.com/users/mbhave/orgs",
                "repos_url": "https://api.github.com/users/mbhave/repos",
                "events_url": "https://api.github.com/users/mbhave/events{/privacy}",
                "received_events_url": "https://api.github.com/users/mbhave/received_events",
                "type": "User",
                "site_admin": false
            },
            "assignees": [
                {
                    "login": "mbhave",
                    "id": 1761408,
                    "node_id": "MDQ6VXNlcjE3NjE0MDg=",
                    "avatar_url": "https://avatars0.githubusercontent.com/u/1761408?v=4",
                    "gravatar_id": "",
                    "url": "https://api.github.com/users/mbhave",
                    "html_url": "https://github.com/mbhave",
                    "followers_url": "https://api.github.com/users/mbhave/followers",
                    "following_url": "https://api.github.com/users/mbhave/following{/other_user}",
                    "gists_url": "https://api.github.com/users/mbhave/gists{/gist_id}",
                    "starred_url": "https://api.github.com/users/mbhave/starred{/owner}{/repo}",
                    "subscriptions_url": "https://api.github.com/users/mbhave/subscriptions",
                    "organizations_url": "https://api.github.com/users/mbhave/orgs",
                    "repos_url": "https://api.github.com/users/mbhave/repos",
                    "events_url": "https://api.github.com/users/mbhave/events{/privacy}",
                    "received_events_url": "https://api.github.com/users/mbhave/received_events",
                    "type": "User",
                    "site_admin": false
                }
            ],
            "milestone": {
                "url": "https://api.github.com/repos/spring-projects/spring-boot/milestones/130",
                "html_url": "https://ExternalResourcegithub.com/spring-projects/spring-boot/milestone/130",
                "labels_url": "https://api.github.com/repos/spring-projects/spring-boot/milestones/130/labels",
                "id": 3865602,
                "node_id": "MDk6TWlsZXN0b25lMzg2NTYwMg==",
                "number": 130,
                "title": "2.2.0.M1",
                "description": "",
                "creator": {
                    "login": "snicoll",
                    "id": 490484,
                    "node_id": "MDQ6VXNlcjQ5MDQ4NA==",
                    "avatar_url": "https://avatars0.githubusercontent.com/u/490484?v=4",
                    "gravatar_id": "",
                    "url": "https://api.github.com/users/snicoll",
                    "html_url": "https://github.com/snicoll",
                    "followers_url": "https://api.github.com/users/snicoll/followers",
                    "following_url": "https://api.github.com/users/snicoll/following{/other_user}",
                    "gists_url": "https://api.github.com/users/snicoll/gists{/gist_id}",
                    "starred_url": "https://api.github.com/users/snicoll/starred{/owner}{/repo}",
                    "subscriptions_url": "https://api.github.com/users/snicoll/subscriptions",
                    "organizations_url": "https://api.github.com/users/snicoll/orgs",
                    "repos_url": "https://api.github.com/users/snicoll/repos",
                    "events_url": "https://api.github.com/users/snicoll/events{/privacy}",
                    "received_events_url": "https://api.github.com/users/snicoll/received_events",
                    "type": "User",
                    "site_admin": false
                },
                "open_issues": 0,
                "closed_issues": 140,
                "state": "closed",
                "created_at": "2018-12-03T08:35:21Z",
                "updated_at": "2019-03-08T16:29:51Z",
                "due_on": "2019-03-08T08:00:00Z",
                "closed_at": "2019-03-08T16:29:51Z"
            },
            "comments": 8,
            "created_at": "2018-07-19T07:50:14Z",
            "updated_at": "2019-10-09T14:38:58Z",
            "closed_at": "2019-02-28T21:17:39Z",
            "author_association": "MEMBER",
            "body": "`@AutoConfigureMockMvc` has a `secure` attribute that's `true` by default. It is possible for the user to set that to `false` in which case security should not apply.\r\n\r\nIt is currently implemented using an `@Import` that is conditional on the property. This makes that a very unusual construct for importing auto-configurations. The side effect is that if you exclude the security auto-configuration in your app (via `exclude` on  `@SpringBootApplication`) that exclude is ignored, even if #12586 is implemented."
        }
    },
       {
        "id": 2699261743,
        "node_id": "MDE1OlN1YnNjcmliZWRFdmVudDI2OTkyNjE3NDM=",
        "url": "https://api.github.com/repos/spring-projects/spring-boot/issues/events/2699261743",
        "actor": {
            "login": "wilkinsona",
            "id": 914682,
            "node_id": "MDQ6VXNlcjkxNDY4Mg==",
            "avatar_url": "https://avatars3.githubusercontent.com/u/914682?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/wilkinsona",
            "html_url": "https://github.com/wilkinsona",
            "followers_url": "https://api.github.com/users/wilkinsona/followers",
            "following_url": "https://api.github.com/users/wilkinsona/following{/other_user}",
            "gists_url": "https://api.github.com/users/wilkinsona/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/wilkinsona/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/wilkinsona/subscriptions",
            "organizations_url": "https://api.github.com/users/wilkinsona/orgs",
            "repos_url": "https://api.github.com/users/wilkinsona/repos",
            "events_url": "https://api.github.com/users/wilkinsona/events{/privacy}",
            "received_events_url": "https://api.github.com/users/wilkinsona/received_events",
            "type": "User",
            "site_admin": false
        },
        "event": "subscribed",
        "commit_id": null,
        "commit_url": null,
        "created_at": "2019-10-09T14:30:44Z",
        "issue": {
            "url": "https://api.github.com/repos/spring-projects/spring-boot/issues/13822",
            "repository_url": "https://api.github.com/repos/spring-projects/spring-boot",
            "labels_url": "https://api.github.com/repos/spring-projects/spring-boot/issues/13822/labels{/name}",
            "comments_url": "https://api.github.com/repos/spring-projects/spring-boot/issues/13822/comments",
            "events_url": "https://api.github.com/repos/spring-projects/spring-boot/issues/13822/events",
            "html_url": "https://github.com/spring-projects/spring-boot/issues/13822",
            "id": 342616593,
            "node_id": "MDU6SXNzdWUzNDI2MTY1OTM=",
            "number": 13822,
            "title": "@AutoConfigureMockMvc imports auto-configurations manually",
            "user": {
                "login": "snicoll",
                "id": 490484,
                "node_id": "MDQ6VXNlcjQ5MDQ4NA==",
                "avatar_url": "https://avatars0.githubusercontent.com/u/490484?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/snicoll",
                "html_url": "https://github.com/snicoll",
                "followers_url": "https://api.github.com/users/snicoll/followers",
                "following_url": "https://api.github.com/users/snicoll/following{/other_user}",
                "gists_url": "https://api.github.com/users/snicoll/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/snicoll/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/snicoll/subscriptions",
                "organizations_url": "https://api.github.com/users/snicoll/orgs",
                "repos_url": "https://api.github.com/users/snicoll/repos",
                "events_url": "https://api.github.com/users/snicoll/events{/privacy}",
                "received_events_url": "https://api.github.com/users/snicoll/received_events",
                "type": "User",
                "site_admin": false
            },
            "labels": [
                {
                    "id": 16916891,
                    "node_id": "MDU6TGFiZWwxNjkxNjg5MQ==",
                    "url": "https://api.github.com/repos/spring-projects/spring-boot/labels/type:%20bug",
                    "name": "type: bug",
                    "color": "e3d9fc",
                    "default": false
                }
            ],
            "state": "closed",
            "locked": false,
            "assignee": {
                "login": "mbhave",
                "id": 1761408,
                "node_id": "MDQ6VXNlcjE3NjE0MDg=",
                "avatar_url": "https://avatars0.githubusercontent.com/u/1761408?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/mbhave",
                "html_url": "https://github.com/mbhave",
                "followers_url": "https://api.github.com/users/mbhave/followers",
                "following_url": "https://api.github.com/users/mbhave/following{/other_user}",
                "gists_url": "https://api.github.com/users/mbhave/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/mbhave/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/mbhave/subscriptions",
                "organizations_url": "https://api.github.com/users/mbhave/orgs",
                "repos_url": "https://api.github.com/users/mbhave/repos",
                "events_url": "https://api.github.com/users/mbhave/events{/privacy}",
                "received_events_url": "https://api.github.com/users/mbhave/received_events",
                "type": "User",
                "site_admin": false
            },
            "assignees": [
                {
                    "login": "mbhave",
                    "id": 1761408,
                    "node_id": "MDQ6VXNlcjE3NjE0MDg=",
                    "avatar_url": "https://avatars0.githubusercontent.com/u/1761408?v=4",
                    "gravatar_id": "",
                    "url": "https://api.github.com/users/mbhave",
                    "html_url": "https://github.com/mbhave",
                    "followers_url": "https://api.github.com/users/mbhave/followers",
                    "following_url": "https://api.github.com/users/mbhave/following{/other_user}",
                    "gists_url": "https://api.github.com/users/mbhave/gists{/gist_id}",
                    "starred_url": "https://api.github.com/users/mbhave/starred{/owner}{/repo}",
                    "subscriptions_url": "https://api.github.com/users/mbhave/subscriptions",
                    "organizations_url": "https://api.github.com/users/mbhave/orgs",
                    "repos_url": "https://api.github.com/users/mbhave/repos",
                    "events_url": "https://api.github.com/users/mbhave/events{/privacy}",
                    "received_events_url": "https://api.github.com/users/mbhave/received_events",
                    "type": "User",
                    "site_admin": false
                }
            ],
            "milestone": {
                "url": "https://api.github.com/repos/spring-projects/spring-boot/milestones/130",
                "html_url": "https://github.com/spring-projects/spring-boot/milestone/130",
                "labels_url": "https://api.github.com/repos/spring-projects/spring-boot/milestones/130/labels",
                "id": 3865602,
                "node_id": "MDk6TWlsZXN0b25lMzg2NTYwMg==",
                "number": 130,
                "title": "2.2.0.M1",
                "description": "",
                "creator": {
                    "login": "snicoll",
                    "id": 490484,
                    "node_id": "MDQ6VXNlcjQ5MDQ4NA==",
                    "avatar_url": "https://avatars0.githubusercontent.com/u/490484?v=4",
                    "gravatar_id": "",
                    "url": "https://api.github.com/users/snicoll",
                    "html_url": "https://github.com/snicoll",
                    "followers_url": "https://api.github.com/users/snicoll/followers",
                    "following_url": "https://api.github.com/users/snicoll/following{/other_user}",
                    "gists_url": "https://api.github.com/users/snicoll/gists{/gist_id}",
                    "starred_url": "https://api.github.com/users/snicoll/starred{/owner}{/repo}",
                    "subscriptions_url": "https://api.github.com/users/snicoll/subscriptions",
                    "organizations_url": "https://api.github.com/users/snicoll/orgs",
                    "repos_url": "https://api.github.com/users/snicoll/repos",
                    "events_url": "https://api.github.com/users/snicoll/events{/privacy}",
                    "received_events_url": "https://api.github.com/users/snicoll/received_events",
                    "type": "User",
                    "site_admin": false
                },
                "open_issues": 0,
                "closed_issues": 140,
                "state": "closed",
                "created_at": "2018-12-03T08:35:21Z",
                "updated_at": "2019-03-08T16:29:51Z",
                "due_on": "2019-03-08T08:00:00Z",
                "closed_at": "2019-03-08T16:29:51Z"
            },
            "comments": 8,
            "created_at": "2018-07-19T07:50:14Z",
            "updated_at": "2019-10-09T14:38:58Z",
            "closed_at": "2019-02-28T21:17:39Z",
            "author_association": "MEMBER",
            "body": "`@AutoConfigureMockMvc` has a `secure` attribute that's `true` by default. It is possible for the user to set that to `false` in which case security should not apply.\r\n\r\nIt is currently implemented using an `@Import` that is conditional on the property. This makes that a very unusual construct for importing auto-configurations. The side effect is that if you exclude the security auto-configuration in your app (via `exclude` on  `@SpringBootApplication`) that exclude is ignored, even if #12586 is implemented."
        }
    }]""")
        )

        val event = githubClient.fetchEventsList("spring-projects", "spring-boot")

        StepVerifier.create(event).assertNext {

            assertThat(it.actor.avatarUrl).isEqualTo("https://avatars0.githubusercontent.com/u/1761408?v=4")
            assertThat(it.actor.htmlUrl).isEqualTo("https://github.com/mbhave")
            assertThat(it.actor.login).isEqualTo("mbhave")
            assertThat(it.issue.title).isEqualTo("@AutoConfigureMockMvc imports auto-configurations manually")
            assertThat(it.issue.number).isEqualTo(13822)

        }.assertNext {

            assertThat(it.actor.avatarUrl).isEqualTo("https://avatars3.githubusercontent.com/u/914682?v=4")
            assertThat(it.actor.htmlUrl).isEqualTo("https://github.com/wilkinsona")
            assertThat(it.actor.login).isEqualTo("wilkinsona")
            assertThat(it.issue.title).isEqualTo("@AutoConfigureMockMvc imports auto-configurations manually")
            assertThat(it.issue.number).isEqualTo(13822)

        }.verifyComplete()
    }
}