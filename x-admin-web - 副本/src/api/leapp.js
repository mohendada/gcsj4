import request from '@/utils/request'

export default {
  getLeaveAppList(searchModel) {
    return request({
      url: '/leaveapplication/list',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        name: searchModel.applicant,
        approval: searchModel.approval
      }
    })
  },
  addApplication(applicant) {
    return request({
      url: '/leaveapplication',
      method: 'post',
      data: applicant
    })
  },
  updateLeaveapplication(applicant) {
    return request({
      url: '/leaveapplication',
      method: 'put',
      data: applicant
    })
  },
  saveApplication(applicant) {
    // eslint-disable-next-line eqeqeq
    if (applicant.id == null && applicant == undefined) {
      return this.addApplication(applicant)
    } else {
      return this.updateLeaveapplication(applicant)
    }
  },
  getLeaveapplicationByid(id) {
    return request({
      url: '/leaveapplication/' + id,
      method: 'get'
    })
  },
  deleteLeaveapplication(id) {
    return request({
      url: '/leaveapplication/' + id,
      method: 'delete'
    })
  }
}
