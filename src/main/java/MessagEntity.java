import java.util.Date;

class MessageEntity  {
    public Long id;
    public Long groupId;
    public String senderId;
    public String message;

    public MessageEntity(Long id, Long groupId, String senderId, String message, Date createTime) {
        this.id = id;
        this.groupId = groupId;
        this.senderId = senderId;
        this.message = message;
    }

    public MessageEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
