package com.technicaltest.technicaltest.utilities.enums

enum class CompanyZoneIdTypes(private val companyZoneId: Int) {
    FIRST_TYPE                          (382),
    SECOND_TYPE                         (402),
    THIRD_TYPE                          (378),
    FOURTH_TYPE                         (473),
    FIFTH_TYPE                          (412),
    SIXTH_TYPE                          (467),
    SEVENTH_TYPE                        (545);

    companion object {
        fun lookup(companyZoneId: Int): CompanyZoneIdTypes? {
            companyZoneId.let {
                values().forEach {
                    if (it.companyZoneId == companyZoneId) {
                        return it
                    }
                }
            }

            return null
        }
    }
}