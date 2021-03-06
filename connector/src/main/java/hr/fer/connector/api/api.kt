package hr.fer.connector.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDateTime


data class CreateTripCmd(@TargetAggregateIdentifier val id: String, val title: String, val description: String, val price: Double, val date: LocalDateTime, val cancelationDate: LocalDateTime)
data class UpdateTripCmd(@TargetAggregateIdentifier val id: String, val title: String, val description: String, val price: Double, val date: LocalDateTime, val cancelationDate: LocalDateTime)
data class CancelTripCmd(@TargetAggregateIdentifier val tripId: String, val adminId: Long, val explanation: String)

data class JoinTripCmd(@TargetAggregateIdentifier val tripId: String, val userId: Long)
data class LeaveTripCmd(@TargetAggregateIdentifier val tripId: String, val userId: Long)
data class LeavePendingTripCmd(@TargetAggregateIdentifier val tripId: String, val userId: Long)
data class AcceptUserToTripCmd(@TargetAggregateIdentifier val tripId: String, val userId: Long)
data class DenyUserToTripCmd(@TargetAggregateIdentifier val tripId: String, val userId: Long)


data class TripCreatedEvt(val id: String, val title: String, val description: String, val price: Double, val date: LocalDateTime, val cancelationDate: LocalDateTime)
data class TripUpdatedEvt(val id: String, val title: String, val description: String, val price: Double, val date: LocalDateTime, val cancelationDate: LocalDateTime)
data class TripCanceledEvt(val tripId: String, val userId: Long, val explanation: String)

data class JoinedTripEvt(val tripId: String, val userId: Long)
data class UserLeftPendingTripEvt(val tripId: String, val userId: Long)
data class UserLeftTripEvt(val tripId: String, val userId: Long)
data class UserAcceptedOnTripEvt(val tripId: String, val userId: Long)
data class UserRefusedOnTripEvt(val tripId: String, val userId: Long)
data class CancelledTripMailSentEvt(val userId: Long, val explanation: String)


