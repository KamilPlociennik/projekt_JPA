	package com.capgemini.wsb.persistence.entity;

	import java.time.LocalDateTime;
	import java.util.ArrayList;
	import javax.persistence.*;
	import java.util.List;

	@Entity
	@Table(name = "VISIT")
	public class VisitEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String description;

		@Column(nullable = false)
		private LocalDateTime time;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "DOCTOR_ID")
		private DoctorEntity doctorEntity;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "patient_id")
		private PatientEntity patientEntity;

		@OneToMany(mappedBy = "visitEntity", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
		private List<MedicalTreatmentEntity> treatments;





		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public LocalDateTime getTime() {
			return time;
		}

		public void setTime(LocalDateTime time) {
			this.time = time;
		}

		public DoctorEntity getDoctorEntity() {
			return doctorEntity;
		}

		public void setDoctorEntity(DoctorEntity doctorEntity) {
			this.doctorEntity = doctorEntity;
		}
		public PatientEntity getPatientEntity() {
			return patientEntity;
		}

		public void setPatientEntity(PatientEntity patientEntity) {
			this.patientEntity = patientEntity;
		}

		public List<MedicalTreatmentEntity> getTreatments() {
			return treatments;
		}

		public void setTreatments(List<MedicalTreatmentEntity> treatments) {
			this.treatments = treatments;
		}
		public void setPatientId(Long patientId) {
			if (this.patientEntity == null) {
				this.patientEntity = new PatientEntity();
			}
			this.patientEntity.setId(patientId);
		}

	}
