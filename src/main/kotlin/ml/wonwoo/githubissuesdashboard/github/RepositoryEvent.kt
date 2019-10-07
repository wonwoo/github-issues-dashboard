package ml.wonwoo.githubissuesdashboard.github

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

data class RepositoryEvent(

    @JsonProperty("event")
    val type: Type,

    @JsonProperty("created_at")
    val creationTime: OffsetDateTime,

    @JsonProperty("actor")
    val actor: Actor,

    @JsonProperty("issue")
    val issue: Issue
)


enum class Type {

    @JsonProperty("closed")
    CLOSED,
    @JsonProperty("reopened")
    REOPENED,
    @JsonProperty("subscribed")
    SUBSCRIBED,
    @JsonProperty("unsubscribed")
    UNSUBSCRIBED,
    @JsonProperty("merged")
    MERGED,
    @JsonProperty("referenced")
    REFERENCED,
    @JsonProperty("mentioned")
    MENTIONED,
    @JsonProperty("assigned")
    ASSIGNED,
    @JsonProperty("unassigned")
    UNASSIGNED,
    @JsonProperty("labeled")
    LABELED,
    @JsonProperty("unlabeled")
    UNLABELED,
    @JsonProperty("milestoned")
    MILESTONED,
    @JsonProperty("demilestoned")
    DEMILESTONED,
    @JsonProperty("renamed")
    RENAMED,
    @JsonProperty("locked")
    LOCKED,
    @JsonProperty("unlocked")
    UNLOCKED,
    @JsonProperty("head_ref_deleted")
    HEAD_REF_DELETED,
    @JsonProperty("head_ref_force_pushed")
    HEAD_REF_FORCE_PUSHED,
    @JsonProperty("head_ref_restored")
    HEAD_REF_RESTORED,
    @JsonProperty("converted_note_to_issue")
    CONVERTED_NOTE_TO_ISSUE,
    @JsonProperty("moved_columns_in_project")
    MOVED_COLUMNS_IN_PROJECT,
    @JsonProperty("review_requested")
    REVIEW_REQUESTED,
    @JsonProperty("transferred")
    TRANSFERRED

}