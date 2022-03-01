export default {
  getLocation() {
    let Ip = returnCitySN['cip']
    let cityname = returnCitySN['cname']
    return {ip: Ip, cityName: cityname}
  }
}
