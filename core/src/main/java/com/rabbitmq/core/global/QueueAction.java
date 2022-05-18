package com.rabbitmq.core.global;

import com.rabbitmq.core.global.Constants.EVENT_CODE;

public class QueueAction<T> {


  private String actionId;
  private T dto;
  private EVENT_CODE actionCode;

  public static QueueAction.QueueActionBuilder builder() {
    return new QueueAction.QueueActionBuilder();
  }

  public String getActionId() {
    return actionId;
  }

  public void setActionId(final String actionId) {
    this.actionId = actionId;
  }


  public Object getDto() {
    return dto;
  }

  public void setDto(final T dto) {
    this.dto = dto;
  }

  public EVENT_CODE getActionCode() {
    return actionCode;
  }

  public void setActionCode(final EVENT_CODE actionCode) {
    this.actionCode = actionCode;
  }

  public QueueAction(final String actionId, final T dto,
      final EVENT_CODE actionCode) {
    this.actionId = actionId;
    this.dto = dto;
    this.actionCode = actionCode;
  }

  public static class QueueActionBuilder<T> {

    private String actionId;
    private T dto;
    private EVENT_CODE actionCode;

    QueueActionBuilder() {

    }

    public QueueAction.QueueActionBuilder actionId(final String actionId) {
      this.actionId = actionId;
      return this;
    }

    public QueueAction.QueueActionBuilder dto(final T dto) {
      this.dto = dto;
      return this;
    }

    public QueueAction.QueueActionBuilder actionCode(final EVENT_CODE actionCode) {
      this.actionCode = actionCode;
      return this;
    }

    public QueueAction build() {
      return new QueueAction(this.actionId, this.dto, this.actionCode);
    }

    public String toString() {
      return "QueueAction.QueueActionBuilder(actionId=" + this.actionId + ", object=" + this.dto + ", actionCode=" + this.actionCode;
    }
  }
}
