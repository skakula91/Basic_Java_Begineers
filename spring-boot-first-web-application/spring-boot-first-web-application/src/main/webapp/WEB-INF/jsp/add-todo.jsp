<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<div class="container">
  <form:form method="post" modelAttribute ="todo">
      <%--    hidden fields--%>
      <form:hidden path="id"/>
      <fieldset class="form-group">
          <form:label path="desc">Description</form:label>
          <form:input path="desc" type="text" name="desc" class="form-control" required="required"/>
          <form:errors path="desc" cssClass="text-warning"></form:errors>
      </fieldset>
      <fieldset class="form-group">
          <form:label path="targetDate">TargetDate</form:label>
          <form:input path="targetDate" id="targetDate" type="text" name="desc" class="form-control" required="required"/>
          <form:errors path="targetDate" cssClass="text-warning"></form:errors>
      </fieldset>
      <button type="submit" class="btn btn-success">Submit</button>
  </form:form>
</div>
<%@include file="common/footer.jspf"%>
